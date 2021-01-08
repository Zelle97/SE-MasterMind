package mastermind.controller

import mastermind.model.{Attempt, Color, ColorInterface, GameData, GameDataInterface}
import mastermind.util.{GameOver, InGame, UndoManager, Win}

import scala.swing.Publisher
import scala.util.{Failure, Success, Try}

class Controller(var gameData: GameDataInterface,
                 var color: ColorInterface,
                 var turn: Int = 0) extends ControllerInterface with Publisher {

  private val undoManager = new UndoManager

  def difficultyMatcher(difficulty: String): Option[String] = difficulty match {
    case "easy" => Some("easy")
    case "medium" => Some("medium")
    case "mastermind" => Some("mastermind")
    case _ => None
  }

  def setDifficulty(difficultyInput: String): Unit = {
    val difficulty = difficultyMatcher(difficultyInput)

    Try(GameData(DifficultyStrategy.getAttempts(difficulty.get), color.pickSolution())) match {
      case Success(newGameDate) =>
        gameData = newGameDate
        turn = 0
        publish(new InGame)
      case Failure(exception) =>
        print("Invalid Difficulty\n")
        print("Please use easy, medium or mastermind")
        print("\n")
    }
  }

  override def addAttempt(input: String): Unit = {
    val colors = input.split(" ").toVector
    Try(Attempt(colors.map(colorInput => color.apply(colorInput).get))) match {
      case Success(filledSuccess) =>
        if(filledSuccess.userPickedColors.size < 4) {
          print("Invalid Input\n")
        } else {
          undoManager.doStep(new AddCommand(gameData, filledSuccess, this))
          if (gameData.getAttempt(gameData.getAttemptSize() - turn).getCorrectPositions(gameData.getSolution()) == 4) {
            publish(new Win)
            //System.exit(1)
          } else if (turn == gameData.getAttemptSize()) {
            publish(new GameOver)
            //System.exit(1)
          } else {
            publish(new InGame)
          }
        }
      case Failure(exception) =>
        print("Invalid Input\n")
        print("Please use those colors: ")
        color.getAllColors.foreach(shade => print(shade + " "))
        print("\n")
    }
  }

  override def undo(): Unit = {
    undoManager.undoStep()
    publish(new InGame)
  }

  override def redo(): Unit = {
    undoManager.redoStep()
    publish(new InGame)
  }

  def gameToString: String = {
    gameData.toString()
  }

}
