package mastermind.model.attemptComponent.attemptBaseImpl

import com.google.inject.Inject
import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.colorComponent.ColorInterface
import mastermind.model.colorComponent.colorBaseImpl.Color

case class Attempt @Inject()(override val userPickedColors: Vector[Color] = Vector(Color(), Color(), Color(), Color())) extends AttemptInterface {
  override def getCorrectColors(solution: Vector[ColorInterface]): Int = solution.map(solutionColor => userPickedColors.contains(solutionColor)).count(e => e)
  override def getCorrectPositions(solution: Vector[ColorInterface]): Int = solution.zip(userPickedColors).map(t => t._1.equals(t._2)).count(e => e)
}
