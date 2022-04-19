package mastermind.persistence.fileIOComponent

import mastermind.core.model.gameDataComponent.GameDataInterface
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData

trait FileIOInterface {
  def load: GameData
  def save(gameData: GameDataInterface): Unit
}
