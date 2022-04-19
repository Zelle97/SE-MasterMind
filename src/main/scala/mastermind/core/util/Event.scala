package mastermind.core.util

import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.core.model.gameDataComponent.GameDataInterface

import scala.swing.event.Event

case class InGame(gameData: GameData) extends Event
case class Win(gameData: GameData) extends Event
case class GameOver(gameData: GameData) extends Event
