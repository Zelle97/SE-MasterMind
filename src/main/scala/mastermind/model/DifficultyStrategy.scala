package mastermind.model

object DifficultyStrategy {

  def getAttempts(difficulty: String) = difficulty match {
    case "easy" => strategy1
    case "medium" => strategy2
    case "mastermind" => strategy3
    case _ => strategy1
  }

  def strategy1 = Vector[Attempt](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())

  def strategy2 = Vector[Attempt](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())

  def strategy3 = Vector[Attempt](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())

}
