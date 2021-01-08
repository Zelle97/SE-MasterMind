package mastermind.model

import mastermind.model.Color.Shade

case class Attempt(userPickedColors: Vector[Shade] = Vector(Shade(), Shade(), Shade(), Shade())) extends AttemptInterface {

  override def getCorrectColors(solution: Vector[Shade]): Int = {
    solution.map(solutionColor => userPickedColors.contains(solutionColor)).count(e => e)
  }

  override def getCorrectPositions(solution: Vector[Shade]): Int = {
    solution.zip(userPickedColors).map(t => t._1.equals(t._2)).count(e => e)
  }

  override def getUserPickedColor(index:Int): Shade = {
    userPickedColors(index)
  }

  override def getAllUserColors(): Vector[Shade] = {
    userPickedColors
  }
}
