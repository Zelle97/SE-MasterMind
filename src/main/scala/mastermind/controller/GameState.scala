package mastermind.controller

import mastermind.util.{Event, GameOver, InGame, Win}

object GameState {
  var state: String = inGame

  def handle(e: Event): String = {
    e match {
      case d: InGame => state = inGame
      case w: Win => state = win
      case l: GameOver => state = gameOver
    }
    state
  }

  def inGame = "I am in Game"


  def win = "!!Win!! You are a true Mastermind!!!"


  def gameOver = "!!Game over!! You are a looser!!!"

}
