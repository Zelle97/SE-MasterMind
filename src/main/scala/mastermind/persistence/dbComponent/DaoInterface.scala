package mastermind.persistence.dbComponent

import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData

import scala.util.Try

trait DaoInterface {
  def load: GameData
  def save(gameData: GameData): Unit
}
