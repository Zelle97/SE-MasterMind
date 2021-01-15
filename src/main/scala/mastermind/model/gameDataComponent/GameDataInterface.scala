package mastermind.model.gameDataComponent

import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.colorComponent.colorBaseImpl.Shade
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData

trait GameDataInterface {
  def updateAttempt(index: Int, attempt: AttemptInterface): GameData

  def getAttemptSize(): Int

  def getAllAttempts(): Vector[AttemptInterface]

  def getAttempt(index: Int): AttemptInterface

  def getSolution(): Vector[Shade]

  def getTurn(): Int

  def setTurn(turn: Int): Unit
}
