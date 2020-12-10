package mastermind.controller

import mastermind.model.Color.Shade
import mastermind.model.{Attempt, GameData}
import mastermind.util.{GameOver, InGame, Observable, UndoManager, Win}

class Controller(var gameData: GameData, var turn: Int = 0) extends Observable {

  private val undoManager = new UndoManager

  def addAttempt(input: String): Unit = {
    val colors = input.split(" ").toVector
    val attempt = Attempt(colors.map(color => Shade.apply(color)))
    undoManager.doStep(new AddCommand(gameData, attempt, this))
    notifyObservers

    if (gameData.attempts(gameData.attempts.size - turn).getCorrectPositions(gameData.solution) == 4) {
      println(GameState.handle(Win()))
      //System.exit(1)
    }

    if (turn == gameData.attempts.size) {
      println(GameState.handle(GameOver()))
      //System.exit(1)
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
