package mastermind

case class GameData(attempts: Vector[Attempt] = Vector[Attempt]()) {
  def addAttempt(attempt: Attempt): GameData = {
    GameData(attempts :+ attempt)
  }
  def updateAttempt(index: Int, attempt:Attempt): GameData = {
    GameData(attempts.updated(index, attempt))
  }
}
