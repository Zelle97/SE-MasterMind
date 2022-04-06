package mastermind.util

import mastermind.model.gameDataComponent.GameDataInterface
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData

import scala.swing.event.Event

case class InGame(gameData: GameData) extends Event
case class Win(gameData: GameData) extends Event
case class GameOver(gameData: GameData) extends Event
