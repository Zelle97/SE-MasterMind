package mastermind.model.attemptComponent

import mastermind.model.colorComponent.colorBaseImpl.Color


trait AttemptInterface {
  def getUserPickedColor(index: Int): Color

  def getAllUserColors(): Vector[Color]

  def getCorrectColors(solution: Vector[Color]): Int

  def getCorrectPositions(solution: Vector[Color]): Int
}
