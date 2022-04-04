package mastermind.model.gameDataComponent

import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.colorComponent.colorBaseImpl.Color
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData

trait GameDataInterface {
  def updateAttempt(index: Int, attempt: AttemptInterface): GameData

  def getAttemptSize(): Int

  def getAllAttempts(): Vector[AttemptInterface]

  def getAttempt(index: Int): AttemptInterface

  def getSolution(): Vector[Color]

  def getTurn(): Int

  def setTurn(turn: Int): Unit
}
