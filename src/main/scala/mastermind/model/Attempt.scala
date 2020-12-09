package mastermind.model

import mastermind.model.Color.Shade

case class Attempt(userPickedColors: Vector[Shade] = Vector(Shade(), Shade(), Shade(), Shade())) {

  def getCorrectColors(solution: Vector[Shade]): Int = {
    userPickedColors.map(userColor => solution.contains(userColor)).count(e => e)
  }

  def getCorrectPositions(solution: Vector[Shade]): Int = {
    solution.zip(userPickedColors).map(t => t._1.equals(t._2)).count(e => e)
  }
}
