package mastermind.model

import mastermind.model.Color.Shade

case class GameData(attempts: Vector[AttemptInterface],
                    solution: Vector[Shade]) extends GameDataInterface {

  override def updateAttempt(index: Int, attempt: AttemptInterface): GameData = {
    GameData(attempts.updated(index, attempt), solution)
  }

  override def toString(): String = {
    GameBoard(GameData(attempts, solution)).gameToString().gamefield
  }

  override def getAttemptSize(): Int = {
    attempts.size
  }

  override def getAttempt(index: Int): AttemptInterface = {
    attempts(index)
  }

  override def getSolution(): Vector[Shade] = {
    solution
  }

  override def getAllAttempts(): Vector[AttemptInterface] = {
    attempts
  }
}
