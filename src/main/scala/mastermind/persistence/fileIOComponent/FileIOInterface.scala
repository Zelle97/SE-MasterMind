package mastermind.persistence.fileIOComponent

import mastermind.core.model.gameDataComponent.GameDataInterface
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import play.api.libs.json.JsObject

trait FileIOInterface {
  def load: GameData
  def save(gameData: GameDataInterface): Unit
  def gameDataToJson(gameData: GameDataInterface): JsObject
}
