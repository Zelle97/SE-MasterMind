package mastermind.model.gameDataComponent

import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.colorComponent.ColorInterface
import mastermind.model.colorComponent.colorBaseImpl.Color

trait GameDataInterface {
  def attempts: Vector[AttemptInterface]
  def solution: Vector[ColorInterface]
  def turn: Int
  def updateAttempt(index: Int, attempt: AttemptInterface): GameDataInterface
  def getAttempt(index: Int): AttemptInterface
  def toString: String
}
