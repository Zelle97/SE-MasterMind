package mastermind.controller

import mastermind.model.{Attempt, Color, DifficultyStrategy, GameData}
import mastermind.util.{GameOver, Observable, UndoManager, Win}

import scala.util.{Failure, Success, Try}

class Controller(var gameData: GameData =
                 GameData(
                   DifficultyStrategy.getAttempts("easy"),
                   ColorPicker().pickSolution()
                 ),
                 var turn: Int = 0) extends Observable {

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
        notifyObservers
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
        notifyObservers
        if (gameData.attempts(gameData.attempts.size - turn).getCorrectPositions(gameData.solution) == 4) {
          println(GameState.handle(Win()))
          //System.exit(1)
        }
        if (turn == gameData.attempts.size) {
          println(GameState.handle(GameOver()))
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
    notifyObservers
  }

  def redo(): Unit = {
    undoManager.redoStep()
    notifyObservers
  }

  def gameToString: String = {
    gameData.toString()
  }

}
