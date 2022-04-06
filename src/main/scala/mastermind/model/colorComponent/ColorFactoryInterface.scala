package mastermind.model.colorComponent

import mastermind.model.colorComponent.colorBaseImpl.Color

trait ColorFactoryInterface() {
  def getColor(colorString: String): Option[Color]
  def getAllColors(): Vector[Color]
  def pickRandomColor(alreadyPickedColors: Vector[Color]): Color
  def addRandomColor(current: Vector[Color]): Vector[Color]
  def pickSolution(): Vector[Color]
}
