package mastermind.controller

import scala.util.Random

case class ColorPicker(colors: Vector[String] = Vector("red", "blue", "green", "yellow", "violett", "cyan", "black", "white")) {

  def pickSolution(): Vector[String] = {
    addRandomColor(addRandomColor(addRandomColor(addRandomColor(Vector()))))
  }

  def addRandomColor(current: Vector[String]): Vector[String] = {
    current :+ pickRandomColor(current)
  }

  def pickRandomColor(alreadyPickedColors: Vector[String]): String = {
    colors.filterNot(color => alreadyPickedColors.contains(color))(Random.nextInt(colors.size - alreadyPickedColors.size))
  }

}
