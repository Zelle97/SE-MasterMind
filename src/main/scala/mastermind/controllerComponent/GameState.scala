package mastermind.controllerComponent

import mastermind.util.{GameOver, InGame, Win}

import scala.swing.event.Event

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

  def win = "!!Win!! You are a true MasterMind!!!"

  def gameOver = "!!Game over!! You lost the game!!!"

}
