package mastermind.controller

import mastermind.model.{Attempt, Color, ColorFactory, GameData}
import mastermind.util.{GameOver, InGame, Observable, Win}

class Controller(var gameData: GameData, var turn: Int = 0) extends Observable {

  def addAttempt(input: String): Unit = {
    val colors = input.split(" ").toVector
    val attempt = Attempt(colors.map(color => ColorFactory.getColor(color)))
    gameData = gameData.updateAttempt(gameData.attempts.size - turn - 1, attempt)
    turn = turn + 1

    notifyObservers

    if (gameData.attempts(gameData.attempts.size - turn).getCorrectPositions(gameData.solution) == 4){
      println(GameState.handle(Win()))
      System.exit(1)
    }

    if (turn == gameData.attempts.size){
      println(GameState.handle(GameOver()))
      System.exit(1)
    }
  }

  def gameToString(): String = {
    gameData.toString
  }

}