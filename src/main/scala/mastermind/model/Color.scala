package mastermind.model

object Color {
  val allColors = Vector("red", "blue", "green", "yellow", "black", "white", "orange", "brown")

  def apply(colorString: String): Shade = {
    if (allColors.contains(colorString)) {
      Shade(colorString)
    } else {
      Shade()
    }
  }

  def getAllColors: Vector[Shade] = {
    allColors.map(colorString => Shade(colorString))
  }

  case class Shade(colorString: String = "          ") {
    def getColor: String = {
      f"$colorString%10s"
    }

    override def equals(that: Any): Boolean = {
      that match {
        case that: Shade => that.getColor.equals(f"$colorString%10s")
        case _ => false
      }
    }
  }

}
