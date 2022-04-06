package mastermind.controllerComponent

import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt

object DifficultyStrategy {
  def getAttempts(difficulty: String): Vector[Attempt] = difficulty match {
    case "easy" => strategy1
    case "medium" => strategy2
    case "mastermind" => strategy3
  }
  def getAttempts(): Vector[Attempt] = strategy1
  def createDifficultyStrategy(attemptCounter: Int): Vector[Attempt] = Vector.fill(attemptCounter)(Attempt())
  def strategy1: Vector[Attempt] = createDifficultyStrategy(10)
  def strategy2: Vector[Attempt] = createDifficultyStrategy(8)
  def strategy3: Vector[Attempt] = createDifficultyStrategy(7)
}
