package mastermind.persistence

import mastermind.core.DifficultyStrategy
import mastermind.core.model.attemptComponent.attemptBaseImpl
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import mastermind.core.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.persistence.dbComponent.mongoImpl.MongoImpl
import mastermind.persistence.dbComponent.slickImpl.SlickImpl
import mastermind.persistence.fileIOComponent.fileIOJsonImpl.FileIO
import mastermind.persistence.fileIOComponent.fileIOXmlImpl
import org.mockito.Mock
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class dpComponentSpec extends AnyWordSpec with Matchers  {

  "Mongo DB access" when {
    val colorFactory = ColorFactory()
    val solution = colorFactory.pickSolution()
    var attempts = DifficultyStrategy.getAttempts("easy")
    var userAttempt = Vector[Color]()
    userAttempt = userAttempt :+ colorFactory.getColor("red").get :+ colorFactory.getColor("green").get :+ colorFactory.getColor("yellow").get :+ colorFactory.getColor("black").get
    attempts = attempts.updated(0, attemptBaseImpl.Attempt(userAttempt))
    val testData = GameData(attempts, solution)
    val db = new MongoImpl
    "gameData is Saved" should {
      "save the data" in {
        db.save(testData)
      }
    }
    "gameData is Loaded" should {
      "create GameData" in {
        val gameData = db.load
        gameData.attempts.size shouldBe 10
        gameData.attempts(0).userPickedColors(0).colorString shouldBe "red"
        gameData.attempts(0).userPickedColors(1).colorString shouldBe "green"
        gameData.attempts(0).userPickedColors(2).colorString shouldBe "yellow"
        gameData.attempts(0).userPickedColors(3).colorString shouldBe "black"
      }
    }
  }
  "DB access with Slick" when {
    val colorFactory = ColorFactory()
    val solution = colorFactory.pickSolution()
    var attempts = DifficultyStrategy.getAttempts("easy")
    var userAttempt = Vector[Color]()
    userAttempt = userAttempt :+ colorFactory.getColor("red").get :+ colorFactory.getColor("green").get :+ colorFactory.getColor("yellow").get :+ colorFactory.getColor("black").get
    attempts = attempts.updated(0, attemptBaseImpl.Attempt(userAttempt))
    val testData = GameData(attempts, solution)
    val db = new SlickImpl
    "gameData is Saved" should {
      "save the data" in {
        db.save(testData)
      }
    }
    "gameData is Loaded" should {
      "create GameData" in {
        val gameData = db.load
        gameData.attempts.size shouldBe 10
        gameData.attempts(0).userPickedColors(0).colorString shouldBe "red"
        gameData.attempts(0).userPickedColors(1).colorString shouldBe "green"
        gameData.attempts(0).userPickedColors(2).colorString shouldBe "yellow"
        gameData.attempts(0).userPickedColors(3).colorString shouldBe "black"
      }
    }

  }
}

