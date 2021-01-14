package mastermind.model.colorComponent

import mastermind.model.colorComponent.colorBaseImpl.Shade


trait ColorInterface {
  def apply(colorString: String): Option[Shade]
  def getAllColors: Vector[Shade]
  def pickSolution(): Vector[Shade]
}
