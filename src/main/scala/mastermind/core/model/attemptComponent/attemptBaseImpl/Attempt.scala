package mastermind.core.model.attemptComponent.attemptBaseImpl

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.google.inject.Inject
import mastermind.core.model.attemptComponent.AttemptInterface
import mastermind.core.model.colorComponent.ColorInterface
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import spray.json.*
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*
import spray.json.{JsValue, RootJsonFormat}
import spray.json.DefaultJsonProtocol.*

case class Attempt @Inject()(override val userPickedColors: Vector[Color] = Vector(Color(), Color(), Color(), Color())) extends AttemptInterface {
  override def getCorrectColors(solution: Vector[ColorInterface]): Int = solution.map(solutionColor => userPickedColors.contains(solutionColor)).count(e => e)
  override def getCorrectPositions(solution: Vector[ColorInterface]): Int = solution.zip(userPickedColors).map(t => t._1.equals(t._2)).count(e => e)
}
/*object Attempt extends SprayJsonSupport{
  implicit val attemptJsonFormat: RootJsonFormat[Attempt] =
    jsonFormat1(Attempt.apply)
}*/
object Attempt {
  implicit val attemptJsonFormat: RootJsonFormat[Attempt] =
    jsonFormat1(Attempt.apply)
  implicit object attemptInterfaceJsonFormat extends RootJsonFormat[AttemptInterface] {
    def write(a: AttemptInterface) = a match {
      case a: Attempt => a.toJson
    }
    def read(value: JsValue) =
    // If you need to read, you will need something in the
    // JSON that will tell you which subclass to use
      val fields = value.asJsObject.fields
      Attempt(
        fields("userPickedColors").convertTo[Vector[Color]]
      )
  }
}

