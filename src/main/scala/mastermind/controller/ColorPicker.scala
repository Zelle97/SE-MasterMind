package mastermind.controller

import mastermind.model.Color

import scala.util.Random

case class ColorPicker() {
  def pickSolution(): Vector[Color.Shade] = {
    addRandomColor(addRandomColor(addRandomColor(addRandomColor(Vector()))))
  }

  def addRandomColor(current: Vector[Color.Shade]): Vector[Color.Shade] = {
    current :+ pickRandomColor(current)
  }

  def pickRandomColor(alreadyPickedColors: Vector[Color.Shade]): Color.Shade = {
    Color.getAllColors.filterNot(color => alreadyPickedColors.contains(color))(Random.nextInt(Color.allColors.size - alreadyPickedColors.size))
  }

}
