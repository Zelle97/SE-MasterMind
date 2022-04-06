package mastermind.model.colorComponent

import mastermind.controllerComponent.GameState
import mastermind.model.colorComponent.colorFactoryBaseImpl.ColorFactory

trait ColorInterface {
  val colorString: String
  def getColor: String
  override def toString: String
  override def equals(that: Any): Boolean
}
