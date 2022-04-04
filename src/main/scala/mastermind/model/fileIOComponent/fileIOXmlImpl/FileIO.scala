package mastermind.model.fileIOComponent.fileIOXmlImpl

import com.google.inject.Inject
import mastermind.controllerComponent.DifficultyStrategy
import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.model.colorComponent.colorBaseImpl.Color
import mastermind.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.model.fileIOComponent.FileIOInterface
import mastermind.model.gameDataComponent.GameDataInterface
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData

import scala.xml.{Elem, PrettyPrinter}

class FileIO  @Inject() extends FileIOInterface {
  override def load: GameDataInterface = {
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

  def gameDataToXml(gameData: GameDataInterface): Elem = {
    <gamedata>
      <solution>
        {for {
        index <- gameData.getSolution().indices
      } yield {
        <color>{gameData.getSolution()(index).colorString}</color>
      }}
      </solution>
      <attempts>
      {for {
        attempt <- gameData.getAllAttempts().indices
      } yield {
        <attempt>
          {for {
          color <- gameData.getAttempt(attempt).getAllUserColors().indices
        } yield {
          <color>{gameData.getAttempt(attempt).getUserPickedColor(color).colorString}</color>
        }}
        </attempt>
      }}
      </attempts>

    </gamedata>

  }
}
