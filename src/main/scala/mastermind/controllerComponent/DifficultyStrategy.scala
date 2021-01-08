package mastermind.controllerComponent

import mastermind.model.attemptComponent.attemptBaseImpl.Attempt

object DifficultyStrategy {

  def getAttempts(): Vector[Attempt] = {
    strategy1
  }

  def getAttempts(difficulty: String): Vector[Attempt] = difficulty match {
    case "easy" => strategy1
    case "medium" => strategy2
    case "mastermind" => strategy3
  }

  def strategy1: Vector[Attempt] = Vector[Attempt](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())

  def strategy2: Vector[Attempt] = Vector[Attempt](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())

  def strategy3: Vector[Attempt] = Vector[Attempt](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())

}
