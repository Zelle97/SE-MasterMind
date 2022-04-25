package mastermind.view

import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._

case class gameDataView(attempts: Vector[Attempt], solution: Vector[Color], turn: Int) {
}

case class attemptView(userPickedColors: Vector[Color]) {}
object attemptView {
  implicit val attemptJsonFormat: RootJsonFormat[attemptView] =
    jsonFormat1(attemptView.apply)
}

case class colorView(colorString: String) {}
object colorView {
  implicit val attemptJsonFormat: RootJsonFormat[colorView] =
    jsonFormat1(colorView.apply)
}
