package mastermind.model.colorComponent.colorFactoryBaseImpl

import com.google.inject.Inject
import mastermind.model.colorComponent.ColorFactoryInterface
import mastermind.model.colorComponent.colorBaseImpl.Color

import scala.util.Random

case class ColorFactory @Inject()() extends ColorFactoryInterface {
  val allColors = Vector("red", "blue", "green", "yellow", "black", "white", "orange", "brown")

  def getColor(colorString: String): Option[Color] =
    if allColors.contains(colorString) then
      Some(Color(colorString))
    else
      None

  def getAllColors(): Vector[Color] =
    allColors.map(colorString => Color(colorString))

  def pickRandomColor(alreadyPickedColors: Vector[Color]): Color =
    this.getAllColors().filterNot(color => alreadyPickedColors.contains(color))(Random.nextInt(this.allColors.size - alreadyPickedColors.size))

  def addRandomColor(current: Vector[Color]): Vector[Color] =
    current :+ pickRandomColor(current)

  def pickSolution(): Vector[Color] =
    addRandomColor(addRandomColor(addRandomColor(addRandomColor(Vector()))))

}


