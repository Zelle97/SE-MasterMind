package mastermind.model

import mastermind.model.Color.Shade

trait ColorInterface {
  def apply(colorString: String): Option[Shade]
  def getAllColors: Vector[Shade]
  def pickSolution(): Vector[Shade]
}
