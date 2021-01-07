package mastermind.model

import mastermind.model.Color.Shade

trait AttemptInterface {
  def getUserPickedColor(index:Int): Shade
  def getAllUserColors(): Vector[Shade]
  def getCorrectColors(solution: Vector[Shade]): Int
  def getCorrectPositions(solution: Vector[Shade]): Int
}
