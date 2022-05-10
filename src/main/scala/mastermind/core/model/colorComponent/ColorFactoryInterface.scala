package mastermind.core.model.colorComponent

import mastermind.core.model.colorComponent.colorBaseImpl.Color

trait ColorFactoryInterface() {
  def getColor(colorString: String): Option[Color]
  def getAllColors(): Vector[Color]
  def pickRandomColor(alreadyPickedColors: Vector[Color]): Color
  def addRandomColor(current: Vector[Color]): Vector[Color]
  def pickSolution(): Vector[Color]
}
