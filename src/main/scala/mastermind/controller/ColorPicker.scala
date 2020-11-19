package mastermind.controller

import mastermind.model.Color

import scala.util.Random

case class ColorPicker() {
  val allColors = Vector("red", "blue", "green", "yellow", "black", "white", "orange", "brown")

  def getAllColors(): Vector[Color] = {
    allColors.map(colorString => Color(colorString))
  }

  def pickSolution(): Vector[Color] = {
    addRandomColor(addRandomColor(addRandomColor(addRandomColor(Vector()))))
  }

  def addRandomColor(current: Vector[Color]): Vector[Color] = {
    current :+ pickRandomColor(current)
  }

  def pickRandomColor(alreadyPickedColors: Vector[Color]): Color = {
    getAllColors().filterNot(color => alreadyPickedColors.contains(color))(Random.nextInt(allColors.size - alreadyPickedColors.size))
  }

}
