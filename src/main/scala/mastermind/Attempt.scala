package mastermind

case class Attempt(userPickedColors: Vector[String] = Vector("          ","          ","          ","          ")) {
  def updateColor(index: Int, color: String): Attempt = {
    Attempt(userPickedColors.updated(index, color))
  }

  def updateAllColor(colors: Vector[String]): Attempt = {
    Attempt(userPickedColors)
      .updateColor(0,f"${colors(0)}%10s")
      .updateColor(1,f"${colors(1)}%10s")
      .updateColor(2,f"${colors(2)}%10s")
      .updateColor(3,f"${colors(3)}%10s")
  }


}