package mastermind.core.model.colorComponent

import mastermind.core.GameState
import mastermind.core.model.colorComponent.colorFactoryBaseImpl.ColorFactory

trait ColorInterface {
  val colorString: String
  def getColor: String
  override def toString: String
  override def equals(that: Any): Boolean
}
