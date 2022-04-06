package mastermind.model.colorComponent.colorFactoryBaseImpl

import com.google.inject.Inject
import mastermind.model.colorComponent.{ColorFactoryInterface, ColorInterface}
import mastermind.model.colorComponent.colorBaseImpl.Color

import scala.util.Random

case class ColorFactory @Inject()() extends ColorFactoryInterface {
  val allColors = Vector("red", "blue", "green", "yellow", "black", "white", "orange", "brown")
  override def getColor(colorString: String): Option[Color] =
    if allColors.contains(colorString) then
      Some(Color(colorString))
    else
      None
  override def getAllColors(): Vector[Color] = allColors.map(colorString => Color(colorString))
  override def pickRandomColor(alreadyPickedColors: Vector[Color]): Color =
    this.getAllColors().filterNot(color => alreadyPickedColors.contains(color))(Random.nextInt(this.allColors.size - alreadyPickedColors.size))
  override def addRandomColor(current: Vector[Color]): Vector[Color] = current :+ pickRandomColor(current)
  override def pickSolution(): Vector[Color] = addRandomColor(addRandomColor(addRandomColor(addRandomColor(Vector()))))

}


