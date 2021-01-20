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

  def strategy1: Vector[AttemptInterface] = Vector[AttemptInterface](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())

  def strategy2: Vector[AttemptInterface] = Vector[AttemptInterface](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())

  def strategy3: Vector[AttemptInterface] = Vector[AttemptInterface](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())

}
