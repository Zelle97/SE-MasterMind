package mastermind.model

import scala.util.Random


object Color extends ColorInterface {
  val allColors = Vector("red", "blue", "green", "yellow", "black", "white", "orange", "brown")

  def apply(colorString: String): Option[Shade] = {
    if (allColors.contains(colorString)) {
      Some(Shade(colorString))
    } else {
      None
    }
  }

  def getAllColors: Vector[Shade] = {
    allColors.map(colorString => Shade(colorString))
  }

  def pickSolution(): Vector[Shade] = {
    addRandomColor(addRandomColor(addRandomColor(addRandomColor(Vector()))))
  }

  def addRandomColor(current: Vector[Shade]): Vector[Shade] = {
    current :+ pickRandomColor(current)
  }

  def pickRandomColor(alreadyPickedColors: Vector[Shade]): Shade = {
    Color.getAllColors.filterNot(color => alreadyPickedColors.contains(color))(Random.nextInt(Color.allColors.size - alreadyPickedColors.size))
  }

  case class Shade(colorString: String = "          ") {
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

}
