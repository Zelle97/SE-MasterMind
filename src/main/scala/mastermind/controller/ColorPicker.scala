package mastermind.controller

import mastermind.model.{Color, ColorFactory}

import scala.util.Random

case class ColorPicker() {
  def pickSolution(): Vector[Color] = {
    addRandomColor(addRandomColor(addRandomColor(addRandomColor(Vector()))))
  }

  def addRandomColor(current: Vector[Color]): Vector[Color] = {
    current :+ pickRandomColor(current)
  }

  def pickRandomColor(alreadyPickedColors: Vector[Color]): Color = {
    ColorFactory.getAllColors.filterNot(color => alreadyPickedColors.contains(color))(Random.nextInt(ColorFactory.allColors.size - alreadyPickedColors.size))
  }

}
