package mastermind.core.controller

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.*
import akka.util.ByteString
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse}
import com.google.inject.{Guice, Inject}
import mastermind.MasterMindModule
import spray.json.*
import akka.http.scaladsl.unmarshalling.Unmarshal
import spray.json.DefaultJsonProtocol.*
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*
import mastermind.core.{ControllerInterface, DifficultyStrategy, GameState, GameStateCore}
import net.codingwell.scalaguice.ScalaModule
import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.colorComponent.ColorFactoryInterface
import mastermind.core.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.persistence.fileIOComponent.FileIOInterface
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.core.util.{GameOver, InGame, UndoManager, Win}

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

val persistenceInterface = sys.env.getOrElse("PERSISTENCE_INTERFACE", "localhost")
val persistencePort: Int = sys.env.getOrElse("PERSISTENCE_PORT", 8082).toString.toInt

class Controller @Inject()(override val gameState: GameState, override val colorFactory: ColorFactoryInterface) extends ControllerInterface {
  private val undoManager = new UndoManager
  def difficultyMatcher(difficulty: String): Option[String] = difficulty match
    case "easy" => Some("easy")
    case "medium" => Some("medium")
    case "mastermind" => Some("mastermind")
    case _ => None
  def setDifficulty(difficultyInput: String): Try[String] =
    Try(GameData(DifficultyStrategy.getAttempts(difficultyMatcher(difficultyInput).get), colorFactory.pickSolution())) match {
      case Success(newGameData) =>
        gameState.handle(InGame(newGameData))
        Success("")
      case Failure(exception) =>
        Failure(
          new Exception(s"""
                           |Invalid Difficulty
                           |Please use easy, medium or mastermind
                           |""".stripMargin)
          )
    }
  override def addAttempt(input: String): Try[String] =
    val colors = input.split(" ").toVector
    Try(Attempt(colors.map(colorInput => colorFactory.getColor(colorInput).get))) match {
      case Success(filledSuccess) =>
        if filledSuccess.userPickedColors.size != 4 then
          Failure(new Exception(
            """
              |Invalid Input
              |Wrong amount of colors
              |""".stripMargin))
        else
          val newGameData = undoManager.doStep(new AddCommand(gameState, filledSuccess))
          if newGameData.getCurrentTurn == -1 then
            gameState.handle(GameOver(newGameData))
          else if newGameData.getAttempt(newGameData.getCurrentTurn+1).getCorrectPositions(newGameData.solution) == 4 then
            gameState.handle(Win(newGameData))
          else
            gameState.handle(InGame(newGameData))
          Success("")
      case Failure(exception) =>
        val allColors = new StringBuilder
        colorFactory.getAllColors().foreach(
          shade => allColors.append(shade.toString).append(" ")
        )
          Failure(new Exception(
          s"""
            |Invalid Input
            |Please use those colors:
            |$allColors
            |""".stripMargin))
    }
  override def undo(): Unit =
    val newGameData = undoManager.undoStep(gameState.gameData)
    gameState.handle(InGame(newGameData))
  override def redo(): Unit =
    val newGameData = undoManager.redoStep(gameState.gameData)
    gameState.handle(InGame(newGameData))
  override def save(): Unit =
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    implicit val executionContext = system.executionContext
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = s"http://$persistenceInterface:$persistencePort/game/save",
      entity = HttpEntity(ContentTypes.`application/json`, gameState.gameData.toJson.prettyPrint)
    ))
  override def load(): Unit =
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    implicit val executionContext = system.executionContext
    val response: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.GET,
      uri = s"http://$persistenceInterface:$persistencePort/game/load",
      entity = HttpEntity(ContentTypes.`application/json`, "")
    ))
    undoManager.clearList()
    response.onComplete {
      case Success(response) =>
        Unmarshal(response.entity).to[GameData].onComplete{
          case Success(gameData) => gameState.handle(InGame(gameData))
          case _ =>
        }
      case Failure(exception) => println(exception)
    }
}
