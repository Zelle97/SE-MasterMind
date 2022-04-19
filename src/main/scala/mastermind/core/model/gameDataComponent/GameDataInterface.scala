package mastermind.core.model.gameDataComponent

import mastermind.core.model.attemptComponent.AttemptInterface
import mastermind.core.model.colorComponent.ColorInterface
import mastermind.core.model.colorComponent.colorBaseImpl.Color

trait GameDataInterface {
  def attempts: Vector[AttemptInterface]
  def solution: Vector[ColorInterface]
  def turn: Int
  def updateAttempt(index: Int, attempt: AttemptInterface): GameDataInterface
  def getAttempt(index: Int): AttemptInterface
  def toString: String
}
