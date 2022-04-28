package mastermind.view

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import mastermind.core.GameState
import mastermind.core.util.{GameOver, InGame, Win}
import mastermind.view.ViewInterface
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.*
import scala.util.{ Failure, Success }
import scala.concurrent.Future
import scala.swing.Reactor
import scala.util.{Failure, Success}
import scala.util.matching.Regex
import akka.http.scaladsl.client.RequestBuilding.Post
import spray.json.*
import spray.json.DefaultJsonProtocol.*
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse}

import scala.concurrent.{ExecutionContext, Future}

implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

class TUI() {

  val difficultyPattern: Regex = "(d )(.*)".r


  def processInput(input: String): Unit = {
    input match {
      case "exit" =>  println("Goodbye!")
      case difficultyPattern(_, param) => postDifficulty(param)
      case "z" => postUndo()
      case "y" => postRedo()
      case "s" => //controller.save()
      case "l" => //controller.load()
      case "h" => println(help())
      case _ => postInput(input)
    }
  }

  def postUndo(): Unit = {
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    implicit val executionContext = system.executionContext
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = "http://localhost:8080/game/undo",
      entity = HttpEntity(ContentTypes.`application/json`,"")
    ))
  }

  def postRedo(): Unit = {
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    implicit val executionContext = system.executionContext
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = "http://localhost:8080/game/redo",
      entity = HttpEntity(ContentTypes.`application/json`,"")
    ))
  }

  def postInput(input: String): Unit = {
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    implicit val executionContext = system.executionContext
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = "http://localhost:8080/attempt",
      entity = HttpEntity(ContentTypes.`application/json`, new InputView(input).toJson.prettyPrint)
    ))
  }

  def postDifficulty(diff: String): Unit = {
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    implicit val executionContext = system.executionContext
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = "http://localhost:8080/difficulty",
      entity = HttpEntity(ContentTypes.`application/json`, new DifficultyView(diff).toJson.prettyPrint)
    ))
  }

  def welcome(): Unit =
    println("""
      |Welcome to Mastermind!
      |Please select a difficulty with d easy/medium/mastermind
      |""".stripMargin)

  def help(): String =
    """
    |Welcome to Mastermind!
    |These are the available commands:
    |color1 color2 color3 color4 -> Input colors
    |s -> Save your Game
    |l -> Load your saved Game
    |z -> Undo your last Step
    |y -> Redo your last Step
    |h -> Display this help
    |exit -> Exit the game
    |And here is described how mastermind works:
    |You are the codebreaker: Try to guess the pattern in order and color.
    |There are three different difficulties:
    |easy -> 10 turns
    |medium -> 8 turns
    |mastermind -> 7 turns
    |Each guess is made by placing a row of code pegs on the decoding board.
    |Once placed, you are provided with some feedback on the right side of the row with your guess.
    |Good Luck!!
    |""".stripMargin


  def gameOver(gameString : String): Unit =
    printGame(gameString)
    println("Game Over! Play again by choosing a difficulty: d easy/medium/mastermind or type exit.")

  def win(gameString : String): Unit =
    printGame(gameString)
    println("Win! Play again by choosing a difficulty: d easy/medium/mastermind or type exit.")

  def printGame(gameString : String) : Unit =
    println(gameString)

  def reactToGameState(gameStateView: GameStateView): Unit = {
    gameStateView.state match {
      case "InGame" => printGame(gameStateView.gameString)
      case "Win" => win(gameStateView.gameString)
      case "GameOver" => gameOver(gameStateView.gameString)

    }
  }
}
