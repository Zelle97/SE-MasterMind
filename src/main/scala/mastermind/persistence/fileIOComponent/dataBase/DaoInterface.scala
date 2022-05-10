package mastermind.persistence.fileIOComponent.dataBase

import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData

import scala.util.Try

trait DaoInterface {
    def load(): Try[GameData]
    def save(gameData: GameData) : Unit
}
