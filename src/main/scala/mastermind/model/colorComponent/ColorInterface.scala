package mastermind.model.colorComponent

trait ColorInterface {
  def getColor: String
  override def toString: String
  override def equals(that: Any): Boolean
}
