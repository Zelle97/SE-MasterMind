package mastermind.model.colorComponent.colorBaseImpl

import com.google.inject.Inject
import mastermind.model.colorComponent.ColorInterface

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
