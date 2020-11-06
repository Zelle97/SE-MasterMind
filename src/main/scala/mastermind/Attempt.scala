package mastermind

case class Attempt(userPickedColors: Vector[String] = Vector("          ","          ","          ","          ")) {
  def updateColor(index: Int, color:String):Attempt = {
    Attempt(userPickedColors.updated(index, color))
  }
}
