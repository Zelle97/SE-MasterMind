package mastermind.model

case class GameData(attempts: Vector[Attempt] = Vector[Attempt](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt()),
                   solution: Vector[Color]) {
  def addAttempt(attempt: Attempt): GameData = {
    GameData(attempts :+ attempt, solution)
  }

  def updateAttempt(index: Int, attempt: Attempt): GameData = {
    GameData(attempts.updated(index, attempt), solution)
  }

  override def toString(): String = {
    GameBoard(GameData(attempts, solution), "").gameFieldString().gamefield
  }
}
