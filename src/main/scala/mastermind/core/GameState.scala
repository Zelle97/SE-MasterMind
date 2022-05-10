package mastermind.core

import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.core.util.{GameOver, InGame, Win}

import scala.swing.event.Event

case class GameState(var gameData: GameData) {
  def handle(e: Event): GameState = {
    e match {
      case d: InGame => gameData = d.gameData
      case w: Win => gameData = w.gameData
      case l: GameOver => gameData = l.gameData
    }
    this
  }
}
