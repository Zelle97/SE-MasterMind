package mastermind.core


import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol.*

case class GameStateCore(gameData: GameData, gameString: String, state: String) {}
object GameStateCore {
  implicit val gameStateJsonFormat: RootJsonFormat[GameStateCore] =
    jsonFormat3(GameStateCore.apply)
}

case class Difficulty (diff: String) {}
object Difficulty {
  implicit val difficultyJsonFormat: RootJsonFormat[Difficulty] =
    jsonFormat1(Difficulty.apply)
}

case class Input (input: String) {}
object Input {
  implicit val inputJsonFormat: RootJsonFormat[Input] =
    jsonFormat1(Input.apply)
}
