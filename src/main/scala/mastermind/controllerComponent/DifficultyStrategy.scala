package mastermind.controllerComponent

import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt

object DifficultyStrategy {

  def getAttempts(): Vector[AttemptInterface] = {
    strategy1
  }

  def getAttempts(difficulty: String): Vector[AttemptInterface] = difficulty match {
    case "easy" => strategy1
    case "medium" => strategy2
    case "mastermind" => strategy3
  }

  def createDifficultyStrategy(attemptCounter: Int): Vector[AttemptInterface] = {
    var attemptVector = Vector[AttemptInterface]()
    (1 to attemptCounter).foreach(c => attemptVector = attemptVector:+ Attempt())
    attemptVector
  }

  def strategy1: Vector[AttemptInterface] = createDifficultyStrategy(10)

  def strategy2: Vector[AttemptInterface] = createDifficultyStrategy(8)

  def strategy3: Vector[AttemptInterface] = createDifficultyStrategy(6)

}
