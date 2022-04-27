package mastermind.core.model.gameDataComponent.gameDataBaseImpl

import com.google.inject.Inject
import mastermind.core.GameData
import mastermind.core.model.GameBoard
import mastermind.core.model.attemptComponent.AttemptInterface
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import mastermind.core.model.gameDataComponent.GameDataInterface
import spray.json.DefaultJsonProtocol.*
import spray.json.RootJsonFormat

case class GameData @Inject()(attempts: Vector[AttemptInterface], solution: Vector[Color], turn: Int = 0) extends GameDataInterface {
  def updateAttempt(index: Int, attempt: AttemptInterface): GameData = GameData(attempts.updated(index, attempt), solution, turn)
  def getCurrentTurn: Int = attempts.size - turn - 1
  def getAttempt(index: Int): AttemptInterface = attempts(index)
  override def toString(): String = GameBoard(GameData(attempts, solution, turn)).gameToString().gamefield
}
object GameData {
  implicit val gameDataJsonFormat: RootJsonFormat[GameData] =
    jsonFormat3(GameData.apply)
}
