package mastermind

case class GameData(attempts: Vector[Attempt] = Vector[Attempt]()) {
  def addAttempt(attempt: Attempt): GameData = {
    GameData(attempts :+ attempt)
  }

  def updateAttempt(index: Int, attempt: Attempt): GameData = {
    GameData(attempts.updated(index, attempt))
  }

  def initializeEmpty(): GameData = {
    GameData()
      .addAttempt(Attempt())
      .addAttempt(Attempt())
      .addAttempt(Attempt())
      .addAttempt(Attempt())
      .addAttempt(Attempt())
      .addAttempt(Attempt())
      .addAttempt(Attempt())
      .addAttempt(Attempt())
      .addAttempt(Attempt())
      .addAttempt(Attempt())
  }
}
