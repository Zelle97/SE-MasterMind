package mastermind.model

trait GameDataInterface {
  def updateAttempt(index: Int, attempt: AttemptInterface): GameData
  def getAttemptSize(): Int
  def getAllAttempts(): Vector[AttemptInterface]
  def getAttempt(index:Int):AttemptInterface
  def getSolution():Vector[Color.Shade]
}
