package mastermind.controllerComponent

import mastermind.model.gameDataComponent.GameDataInterface
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.util.{GameOver, InGame, Win}

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
