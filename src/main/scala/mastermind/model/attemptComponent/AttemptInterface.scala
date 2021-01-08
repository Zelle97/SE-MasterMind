package mastermind.model.attemptComponent

import mastermind.model.colorComponent.colorBaseImpl.Color.Shade

trait AttemptInterface {
  def getUserPickedColor(index: Int): Shade

  def getAllUserColors(): Vector[Shade]

  def getCorrectColors(solution: Vector[Shade]): Int

  def getCorrectPositions(solution: Vector[Shade]): Int
}
