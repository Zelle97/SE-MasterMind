package mastermind.core.model.attemptComponent

import mastermind.core.model.colorComponent.ColorInterface
import mastermind.core.model.colorComponent.colorBaseImpl.Color

trait AttemptInterface {
  val userPickedColors: Vector[Color]
  def getCorrectColors(solution: Vector[ColorInterface]): Int
  def getCorrectPositions(solution: Vector[ColorInterface]): Int
}
