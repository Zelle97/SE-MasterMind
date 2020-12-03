package mastermind.model

object ColorFactory {

  val allColors = Vector("red", "blue", "green", "yellow", "black", "white", "orange", "brown")

  def getColor(colorString: String): Color = {
    if(allColors.contains(colorString)) {
      Color(colorString)
    } else {
      Color()
    }
  }

  def getAllColors: Vector[Color] = {
    allColors.map(colorString => Color(colorString))
  }

}
