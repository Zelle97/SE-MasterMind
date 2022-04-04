package mastermind.model.colorComponent.colorBaseImpl

import com.google.inject.Inject
import mastermind.model.colorComponent.ColorInterface

import scala.util.Random

case class Color @Inject()() extends ColorInterface {
  val allColors = Vector("red", "blue", "green", "yellow", "black", "white", "orange", "brown")

  def apply(colorString: String): Option[Shade] =
    if allColors.contains(colorString) then
      Some(Shade(colorString))
    else
      None

  def getAllColors: Vector[Shade] =
    allColors.map(colorString => Shade(colorString))

  def pickSolution(): Vector[Shade] =
    addRandomColor(addRandomColor(addRandomColor(addRandomColor(Vector()))))

  def addRandomColor(current: Vector[Shade]): Vector[Shade] =
    current :+ pickRandomColor(current)

  def pickRandomColor(alreadyPickedColors: Vector[Shade]): Shade =
    this.getAllColors.filterNot(color => alreadyPickedColors.contains(color))(Random.nextInt(this.allColors.size - alreadyPickedColors.size))

}

case class Shade @Inject() (colorString: String = "          ") {
  def getColor: String = {
    f"$colorString%10s"
  }

  override def toString: String = colorString

  override def equals(that: Any): Boolean = {
    that match {
      case that: Shade => that.getColor.equals(f"$colorString%10s")
      case _ => false
    }
  }
}
