package mastermind.controller

import mastermind.model.{Attempt, GameData}
import mastermind.util.Observable

class Controller(var gameData: GameData, var turn: Int = 0) extends Observable {
  def quitGame(): Unit = {

  }

  def addAttempt(input: String): Unit = {
    val colors = input.split(" ").toVector
    val attempt = Attempt(colors.map(color => f"$color%10s"))
    gameData = gameData.updateAttempt(gameData.attempts.size - turn - 1, attempt)
    turn = turn + 1
    notifyObservers
  }

  def gameToString(): String = {
    gameData.toString
  }
}
