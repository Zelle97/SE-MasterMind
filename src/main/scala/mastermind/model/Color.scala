package mastermind.model

case class Color(color: String = "          ") {
  def getColor(): String = {
    f"$color%10s"
  }
  override def equals(that: Any): Boolean = {
    that match {
      case that: Color => that.getColor().equals(f"$color%10s")
      case _ => false
    }
  }
}
