package mastermind.model.gameDataComponent.gameDataBaseImpl

import com.google.inject.Inject
import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.colorComponent.colorBaseImpl.Color
import mastermind.model.gameDataComponent.GameDataInterface
import mastermind.model.GameBoard

case class GameData @Inject()(attempts: Vector[AttemptInterface], solution: Vector[Color], turn: Int = 0) extends GameDataInterface {
  def updateAttempt(index: Int, attempt: AttemptInterface): GameData = GameData(attempts.updated(index, attempt), solution, turn)
  def getCurrentTurn: Int = attempts.size - turn - 1
  def getAttempt(index: Int): AttemptInterface = attempts(index)
  override def toString(): String = GameBoard(GameData(attempts, solution, turn)).gameToString().gamefield
}
