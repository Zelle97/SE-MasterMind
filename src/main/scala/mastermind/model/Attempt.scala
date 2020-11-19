package mastermind.model

case class Attempt(userPickedColors: Vector[Color] = Vector(Color(),Color(),Color(),Color())) {

  def getCorrectColors(solution: Vector[Color]): Int ={
    userPickedColors.map(userColor => solution.contains(userColor)).count(e => e)
  }

  def getCorrectPositions(solution: Vector[Color]): Int ={
    solution.zip(userPickedColors).map(t => t._1.equals(t._2)).count(e => e)
  }
}
