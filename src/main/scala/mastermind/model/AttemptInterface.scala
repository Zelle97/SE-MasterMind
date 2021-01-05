package mastermind.model

import mastermind.model.Color.Shade

trait AttemptInterface {
  def getUserPickedColor(index:Int): Color.Shade
  def getAllUserColors(): Vector[Color.Shade]
  def getCorrectColors(solution: Vector[Shade]): Int
  def getCorrectPositions(solution: Vector[Shade]): Int
}
