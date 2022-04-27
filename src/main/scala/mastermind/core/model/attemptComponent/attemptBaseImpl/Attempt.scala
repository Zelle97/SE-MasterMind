package mastermind.core.model.attemptComponent.attemptBaseImpl

import com.google.inject.Inject
import mastermind.core.Attempt
import mastermind.core.model.attemptComponent.AttemptInterface
import mastermind.core.model.colorComponent.ColorInterface
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import spray.json.DefaultJsonProtocol.*
import spray.json.RootJsonFormat

case class Attempt @Inject()(override val userPickedColors: Vector[Color] = Vector(Color(), Color(), Color(), Color())) extends AttemptInterface {
  override def getCorrectColors(solution: Vector[ColorInterface]): Int = solution.map(solutionColor => userPickedColors.contains(solutionColor)).count(e => e)
  override def getCorrectPositions(solution: Vector[ColorInterface]): Int = solution.zip(userPickedColors).map(t => t._1.equals(t._2)).count(e => e)
}
object Attempt {
  implicit val attemptJsonFormat: RootJsonFormat[Attempt] =
    jsonFormat1(Attempt.apply)
}
