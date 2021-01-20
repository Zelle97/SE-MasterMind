package mastermind.model.fileIOComponent

import mastermind.model.gameDataComponent.GameDataInterface
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData

trait FileIOInterface {
  def load: GameDataInterface
  def save(gameData: GameDataInterface): Unit
}
