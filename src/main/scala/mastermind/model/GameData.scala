package mastermind.model

case class GameData(attempts: Vector[Attempt],
                    solution: Vector[Color.Shade]) {
  def addAttempt(attempt: Attempt): GameData = {
    GameData(attempts :+ attempt, solution)
  }

  def updateAttempt(index: Int, attempt: Attempt): GameData = {
    GameData(attempts.updated(index, attempt), solution)
  }

  override def toString: String = {
    GameBoard(GameData(attempts, solution)).gameToString().gamefield.mkString
  }
}
