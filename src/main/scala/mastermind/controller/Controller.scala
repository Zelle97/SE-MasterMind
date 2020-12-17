package mastermind.controller

import mastermind.model.{Attempt, Color, DifficultyStrategy, GameData}
import mastermind.util.{GameOver, InGame, UndoManager, Win}

import scala.swing.Publisher
import scala.util.{Failure, Success, Try}

class Controller(var gameData: GameData =
                 GameData(
                   DifficultyStrategy.getAttempts("easy"),
                   ColorPicker().pickSolution()
                 ),
                 var turn: Int = 0) extends Publisher {

  private val undoManager = new UndoManager

  def difficultyMatcher(difficulty: String): Option[String] = difficulty match {
    case "easy" => Some("easy")
    case "medium" => Some("medium")
    case "mastermind" => Some("mastermind")
    case _ => None
  }

  def setDifficulty(difficultyInput: String): Unit = {
    val difficulty = difficultyMatcher(difficultyInput)

    Try(GameData(DifficultyStrategy.getAttempts(difficulty.get), ColorPicker().pickSolution())) match {
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

  def addAttempt(input: String): Unit = {
    val colors = input.split(" ").toVector
    Try(Attempt(colors.map(color => Color.apply(color).get))) match {
      case Success(filledSuccess) =>
        undoManager.doStep(new AddCommand(gameData, filledSuccess, this))
        publish(new InGame)
        if (gameData.attempts(gameData.attempts.size - turn).getCorrectPositions(gameData.solution) == 4) {
          publish(new Win)
          //System.exit(1)
        }
        if (turn == gameData.attempts.size) {
          publish(new GameOver)
          //System.exit(1)
        }
      case Failure(exception) =>
        print("Invalid Input\n")
        print("Please use those colors: ")
        Color.getAllColors.foreach(shade => print(shade + " "))
        print("\n")
    }
  }

  def undo(): Unit = {
    undoManager.undoStep()
    publish(new InGame)
  }

  def redo(): Unit = {
    undoManager.redoStep()
    publish(new InGame)
  }

  def gameToString: String = {
    gameData.toString()
  }

}
