package mastermind.model.attemptComponent

import mastermind.model.colorComponent.ColorInterface
import mastermind.model.colorComponent.colorBaseImpl.Color

trait AttemptInterface {
  val userPickedColors: Vector[Color]
  def getCorrectColors(solution: Vector[ColorInterface]): Int
  def getCorrectPositions(solution: Vector[ColorInterface]): Int
}
