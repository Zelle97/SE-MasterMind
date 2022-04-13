package mastermind.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import mastermind.MasterMindModule
import net.codingwell.scalaguice.ScalaModule
import mastermind.controllerComponent.{ControllerInterface, DifficultyStrategy, GameState}
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.model.colorComponent.ColorFactoryInterface
import mastermind.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.model.fileIOComponent.FileIOInterface
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.util.{GameOver, InGame, UndoManager, Win}

import scala.swing.Publisher
import scala.util.{Failure, Success, Try}

class Controller @Inject()(override val gameState: GameState, override val colorFactory: ColorFactoryInterface) extends ControllerInterface with Publisher {
  private val undoManager = new UndoManager
  def difficultyMatcher(difficulty: String): Option[String] = difficulty match
    case "easy" => Some("easy")
    case "medium" => Some("medium")
    case "mastermind" => Some("mastermind")
    case _ => None
  def setDifficulty(difficultyInput: String): Try[String] =
    Try(GameData(DifficultyStrategy.getAttempts(difficultyMatcher(difficultyInput).get), colorFactory.pickSolution())) match {
      case Success(newGameData) =>
        publish(InGame(newGameData))
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
              |To many colors
              |""".stripMargin))
        else
          val newGameData = undoManager.doStep(new AddCommand(gameState, filledSuccess))
          if newGameData.getCurrentTurn == -1 then
            publish(GameOver(newGameData))
          else if newGameData.getAttempt(newGameData.getCurrentTurn+1).getCorrectPositions(newGameData.solution) == 4 then
            publish(Win(newGameData))
          else
            publish(InGame(newGameData))
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
    publish(InGame(newGameData))
  override def redo(): Unit =
    val newGameData = undoManager.redoStep(gameState.gameData)
    publish(InGame(newGameData))
  override def save(): Unit =
    val injector = Guice.createInjector(new MasterMindModule)
    val io = injector.getInstance(classOf[FileIOInterface])
    io.save(gameState.gameData)
  override def load(): Unit =
    val injector = Guice.createInjector(new MasterMindModule)
    val io = injector.getInstance(classOf[FileIOInterface])
    val newGameData = io.load
    undoManager.clearList()
    publish(InGame(newGameData))
}
