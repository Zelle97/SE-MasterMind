package mastermind.core.model.colorComponent.colorBaseImpl

import com.google.inject.Inject
import mastermind.core.model.colorComponent.ColorInterface
import spray.json.DefaultJsonProtocol.*
import spray.json.RootJsonFormat

case class Color @Inject()(override val colorString: String = "          ") extends ColorInterface {
  override def getColor: String = f"$colorString%10s"
  override def toString: String = colorString
  override def equals(that: Any): Boolean = {
    that match {
      case that: Color => that.getColor.equals(f"$colorString%10s")
      case _ => false
    }
  }
}
object Color {
  implicit val colorJsonFormat: RootJsonFormat[Color] =
    jsonFormat1(Color.apply)
}
