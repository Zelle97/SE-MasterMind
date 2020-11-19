package mastermind.model

case class GameData(attempts: Vector[Attempt] = Vector[Attempt](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())) {
  def addAttempt(attempt: Attempt): GameData = {
    GameData(attempts :+ attempt)
  }

  def updateAttempt(index: Int, attempt: Attempt): GameData = {
    GameData(attempts.updated(index, attempt))
  }

  override def toString: String = {
    GameBoard(GameData(attempts), "").gameFieldString().gamefield
  }
}
