package mastermind.model

import mastermind.controllerComponent.DifficultyStrategy
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.model.colorComponent.colorBaseImpl.{Color, Shade}
import mastermind.model.fileIOComponent.fileIOJsonImpl.FileIO
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FileIOSpec extends AnyWordSpec with Matchers {
  "FileIO With Json" when {
    val solution = Color().pickSolution()
    var attempts = DifficultyStrategy.getAttempts("easy")
    var userAttempt = Vector[Shade]()
    userAttempt = userAttempt :+ Color().apply("red").get :+ Color().apply("green").get :+ Color().apply("yellow").get :+ Color().apply("black").get
    attempts = attempts.updated(0, Attempt(userAttempt))
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
        gameData.getAllAttempts().size shouldBe 10
        gameData.getAllAttempts()(0).getUserPickedColor(0).colorString shouldBe "red"
        gameData.getAllAttempts()(0).getUserPickedColor(1).colorString shouldBe "green"
        gameData.getAllAttempts()(0).getUserPickedColor(2).colorString shouldBe "yellow"
        gameData.getAllAttempts()(0).getUserPickedColor(3).colorString shouldBe "black"
      }
    }
  }
}
