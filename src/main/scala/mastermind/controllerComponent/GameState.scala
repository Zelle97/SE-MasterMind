package mastermind.controllerComponent

import mastermind.model.gameDataComponent.GameDataInterface
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.util.{GameOver, InGame, Win}

import scala.swing.event.Event

case class GameState(var state: GameData) {
  def handle(e: Event): GameState = {
    e match {
      case d: InGame => state = d.gameData
      case w: Win => state = w.gameData
      case l: GameOver => state = l.gameData
    }
    this
  }
}
