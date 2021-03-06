package mastermind.model.fileIOComponent

import mastermind.model.gameDataComponent.GameDataInterface

trait FileIOInterface {
  def load: GameDataInterface
  def save(gameData: GameDataInterface): Unit
}
