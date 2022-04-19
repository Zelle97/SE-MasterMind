package mastermind.persistence.fileIOComponent.fileIOJsonImpl

import com.google.inject.Inject
import mastermind.core.DifficultyStrategy
import mastermind.core.model.attemptComponent.AttemptInterface
import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import mastermind.core.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.persistence.fileIOComponent.FileIOInterface
import mastermind.core.model.gameDataComponent.GameDataInterface
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import play.api.libs.json.{JsObject, JsString, JsValue, Json}

import scala.io.{BufferedSource, Source}

class FileIO  @Inject() extends FileIOInterface {
  override def load: GameData = {
    val src: BufferedSource = Source.fromFile("gameData.json")
    val colorFactory = ColorFactory()
    val source: String = src.getLines().mkString
    src.close()
    val json: JsValue = Json.parse(source)
    val attemptsArray = (json \ "attempts").get.as[Array[JsObject]]
    val attemptSize = attemptsArray.size
    var attempts = Vector[AttemptInterface]()
    attemptSize match {
      case 7 => attempts = DifficultyStrategy.getAttempts("mastermind")
      case 8 => attempts = DifficultyStrategy.getAttempts("medium")
      case 10 => attempts = DifficultyStrategy.getAttempts("easy")
    }
    var turn = 0
    attemptsArray
      .foreach(attemptObject => {
        if(!(attemptObject \ "attempt" \ 0 \ "color").get.as[JsString].value.isBlank){
          val attemptIndex = (attemptObject \ "index").get.toString().toInt
          val attempt = (attemptObject \ "attempt").get.as[Array[JsValue]]
          var attemptVector = Vector[Color]()
          attempt.map(jsValueColor => colorFactory.getColor((jsValueColor \ "color").get.as[JsString].value).get).foreach(shade => attemptVector = attemptVector :+ shade)
          attempts = attempts.updated(attemptIndex, Attempt(attemptVector))
          turn = turn + 1
        }
      })

    val solution = (json \ "solution").get.as[Array[JsValue]]
    var solutionVector = Vector[Color]()
    solution.map(jsValueColor => colorFactory.getColor((jsValueColor \ "color").get.as[JsString].value).get).foreach(shade => solutionVector = solutionVector :+ shade)
    GameData(attempts = attempts, solution = solutionVector, turn = turn)
  }

  override def save(gameData: GameDataInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameData.json"))
    pw.write(Json.prettyPrint(gameDataToJson(gameData)))
    pw.close()

  }

  def gameDataToJson(gameData: GameDataInterface): JsValue = {
    Json.obj(
      "solution" -> Json.toJson(
        for {
          index <- gameData.solution.indices
        } yield {
          Json.obj(
            "color" -> Json.toJson(gameData.solution(index).colorString)
          )
        }),
      "attempts" -> Json.toJson(
        for {
          attempt <- gameData.attempts.indices
        } yield {
          Json.obj(
            "index" -> attempt,
            "attempt" -> Json.toJson(
              for {
                color <- gameData.getAttempt(attempt).userPickedColors.indices
              } yield {
                Json.obj(
                  "color" -> Json.toJson(gameData.getAttempt(attempt).userPickedColors(color).colorString)
                )
              })
          )
        }
      )
    )
  }
}
