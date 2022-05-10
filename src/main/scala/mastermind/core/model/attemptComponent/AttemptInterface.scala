package mastermind.core.model.attemptComponent

import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.colorComponent.ColorInterface
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import spray.json.DefaultJsonProtocol.*
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait AttemptInterface {
  val userPickedColors: Vector[Color]
  def getCorrectColors(solution: Vector[ColorInterface]): Int
  def getCorrectPositions(solution: Vector[ColorInterface]): Int
}
