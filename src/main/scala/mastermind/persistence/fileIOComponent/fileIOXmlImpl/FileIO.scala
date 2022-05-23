package mastermind.persistence.fileIOComponent.fileIOXmlImpl

import com.google.inject.Inject
import mastermind.core.DifficultyStrategy
import mastermind.core.model.attemptComponent.AttemptInterface
import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import mastermind.core.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.persistence.fileIOComponent.FileIOInterface
import mastermind.core.model.gameDataComponent.GameDataInterface
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import play.api.libs.json.JsObject

import scala.xml.{Elem, PrettyPrinter}

class FileIO  @Inject() extends FileIOInterface {
  override def load: GameData = {
    val file = scala.xml.XML.loadFile("gameData.xml")
    val colorFactory = ColorFactory()
    val attemptSeq = (file \\ "attempt")
    val attemptSize = attemptSeq.size
    var attempts = Vector[AttemptInterface]()
    attemptSize match {
      case 7 => attempts = DifficultyStrategy.getAttempts("mastermind")
      case 8 => attempts = DifficultyStrategy.getAttempts("medium")
      case 10 => attempts = DifficultyStrategy.getAttempts("easy")
    }
    var i = 0
    var turn = 0
    for(attempt <- attemptSeq) {
      var attemptVector = Vector[Color]()
      val colorSeq = (attempt \\ "color")
      for(color <- colorSeq) {

        if(!color.text.isBlank) {
          val c = colorFactory.getColor(color.text.replaceAll(" ", ""))
          attemptVector = attemptVector:+ c.get
        }
      }
      if(attemptVector.nonEmpty) {
        attempts = attempts.updated(i, Attempt(attemptVector))
        turn= turn + 1
      } else {
        attempts = attempts.updated(i, Attempt())
      }
      i = i+1
    }
    val solution = (file \\ "solution" \ "color")
    var solutionVector = Vector[Color]()
    for(color <- solution) {
      val c = colorFactory.getColor(color.text.replaceAll(" ", ""))
      solutionVector = solutionVector:+ c.get
    }
    GameData(attempts,solutionVector, turn)
  }

  override def save(gameData: GameDataInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameData.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gameDataToXml(gameData))
    pw.write(xml)
    pw.close()
  }

  override def gameDataToJson(gameData: GameDataInterface): JsObject = {
    null
  }

  def gameDataToXml(gameData: GameDataInterface): Elem = {
    <gamedata>
      <solution>
        {for {
        index <- gameData.solution.indices
      } yield {
        <color>{gameData.solution(index).colorString}</color>
      }}
      </solution>
      <attempts>
      {for {
        attempt <- gameData.attempts.indices
      } yield {
        <attempt>
          {for {
          color <- gameData.getAttempt(attempt).userPickedColors.indices
        } yield {
          <color>{gameData.getAttempt(attempt).userPickedColors(color).colorString}</color>
        }}
        </attempt>
      }}
      </attempts>

    </gamedata>

  }
}
