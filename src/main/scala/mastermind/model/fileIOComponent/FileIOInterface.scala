package mastermind.model.fileIOComponent

import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData

trait FileIOInterface {
  def load: GameData
  def save(gameData: GameData): Unit
}
