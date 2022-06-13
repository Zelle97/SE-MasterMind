package mastermind.persistence

import mastermind.core.DifficultyStrategy
import mastermind.core.model.attemptComponent.attemptBaseImpl
import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import mastermind.core.model.colorComponent.colorFactoryBaseImpl.ColorFactory
//import mastermind.persistence.fileIOComponent.fileIOXmlImpl.FileIO
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.persistence.fileIOComponent.fileIOJsonImpl.FileIO
import mastermind.persistence.fileIOComponent.fileIOXmlImpl
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FileIOSpec extends AnyWordSpec with Matchers {
  "FileIO With Json" when {
    val colorFactory = ColorFactory()
    val solution = colorFactory.pickSolution()
    var attempts = DifficultyStrategy.getAttempts("easy")
    var userAttempt = Vector[Color]()
    userAttempt = userAttempt :+ colorFactory.getColor("red").get :+ colorFactory.getColor("green").get :+ colorFactory.getColor("yellow").get :+ colorFactory.getColor("black").get
    attempts = attempts.updated(0, attemptBaseImpl.Attempt(userAttempt))
    val testData = GameData(attempts,solution)
    val io = new FileIO
    "gameData is Saved" should {
      "create a JSON Object" in {
        io.save(testData)
      }
    }
    "gameData is Loaded" should {
      "create GameData" in {
        val gameData = io.load
        gameData.attempts.size shouldBe 10
        gameData.attempts(0).userPickedColors(0).colorString shouldBe "red"
        gameData.attempts(0).userPickedColors(1).colorString shouldBe "green"
        gameData.attempts(0).userPickedColors(2).colorString shouldBe "yellow"
        gameData.attempts(0).userPickedColors(3).colorString shouldBe "black"
      }
    }
  }
  "FileIO With XML" when {
    val colorFactory = ColorFactory()
    val solution = colorFactory.pickSolution()
    var attempts = DifficultyStrategy.getAttempts("easy")
    var userAttempt = Vector[Color]()
    userAttempt = userAttempt :+ colorFactory.getColor("red").get :+ colorFactory.getColor("green").get :+ colorFactory.getColor("yellow").get :+ colorFactory.getColor("black").get
    attempts = attempts.updated(0, attemptBaseImpl.Attempt(userAttempt))
    val testData = GameData(attempts,solution)
    val io = new fileIOXmlImpl.FileIO
    "gameData is Saved" should {
      "create a XML Object" in {
        io.save(testData)
      }
    }
    "gameData is Loaded" should {
      "create GameData" in {
        val gameData = io.load
        gameData.attempts.size shouldBe 10
        gameData.attempts(0).userPickedColors(0).colorString shouldBe "red"
        gameData.attempts(0).userPickedColors(1).colorString shouldBe "green"
        gameData.attempts(0).userPickedColors(2).colorString shouldBe "yellow"
        gameData.attempts(0).userPickedColors(3).colorString shouldBe "black"
      }
    }

  }
}
