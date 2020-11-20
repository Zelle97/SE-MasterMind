package mastermind.controller

import mastermind.model.{Attempt, Color, GameData}
import mastermind.util.Observable

class Controller(var gameData: GameData, var turn: Int = 0) extends Observable {
  def quitGame(): Unit = {
    System.exit(0);
  }

  def addAttempt(input: String): Unit = {
    val colors = input.split(" ").toVector
    val attempt = Attempt(colors.map(color => Color(f"$color%10s")))
    gameData = gameData.updateAttempt(gameData.attempts.size - turn - 1, attempt)
    print(gameData.solution)
    turn = turn + 1
    notifyObservers
  }

  def gameToString(): String = {
    gameData.toString
  }

}
