package mastermind.controllerComponent




import java.nio.file.{Files, Paths}
import mastermind.controllerComponent.controllerBaseImpl.Controller
import mastermind.model.colorComponent.colorBaseImpl.Color
import mastermind.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.util.InGame
import org.mockito.ArgumentMatchers.any
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.Mockito.*

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when {
    val colorFactory = ColorFactory()
    val solution = colorFactory.pickSolution()
    val attempts = DifficultyStrategy.getAttempts("easy")
    "adding an Attempt" should {
      val controllerMock = mock(classOf[ControllerInterface])
      "add a attempt" in {
        controllerMock.addAttempt("red blue yellow green")
        verify(controllerMock.gameState.handle(InGame(any())), times(1));
        //c.gameState.gameData.getAttempt(9).userPickedColors(0).getColor shouldBe "       red"
      }
      "increase the turn" in {
        val before = controllerMock.gameState.gameData.turn
        controllerMock.addAttempt("red blue yellow green")
        before+1 shouldBe controllerMock.gameState.gameData.turn
      }
    }
    "adding another Attempt" should {
      val c = new Controller(GameState(GameData(attempts, solution)), colorFactory)
      "increase the turn" in {
        val before = c.gameState.gameData.turn
        c.addAttempt("red blue yellow green")
        before+1 shouldBe c.gameState.gameData.turn
      }
    }
    "changing the difficulty" should {
      val c = new Controller(GameState(GameData(attempts, solution)), colorFactory)
      "set the GameData size to mastermind" in {
        c.setDifficulty("mastermind")
        c.gameState.gameData.attempts.size shouldBe 7
      }
      "set the GameData size to medium" in {
        c.setDifficulty("medium")
        c.gameState.gameData.attempts.size shouldBe 8
      }
      "set the GameData size to easy" in {
        c.setDifficulty("easy")
        c.gameState.gameData.attempts.size shouldBe 10
      }
    }
    "executing undo" should {
      val c = new Controller(GameState(GameData(attempts, solution)), colorFactory)
      c.addAttempt("red blue yellow green")
      "undo the attempt" in {
        val before = c.gameState.gameData.turn
        c.undo()
        c.gameState.gameData.getAttempt(9).userPickedColors(0).getColor shouldBe "          "
        before-1 shouldBe c.gameState.gameData.turn
      }
    }
    "executing redo" should {
      "redo the previous attempt" in {
        val c = new Controller(GameState(GameData(attempts, solution)), colorFactory)
        c.addAttempt("red blue yellow green")
        val before = c.gameState.gameData.turn
        c.undo()
        c.redo()
        print(c.gameState.gameData.turn)
        print(c.gameState.gameData.attempts)
        c.gameState.gameData.getAttempt(c.gameState.gameData.attempts.size - c.gameState.gameData.turn).userPickedColors(0).getColor shouldBe "       red"
        before shouldBe c.gameState.gameData.turn
      }
    }
    "executing save" should {
      "save the game as a file" in {
        val c = new Controller(GameState(GameData(attempts, solution)), colorFactory)
        c.save()
        val jsonFile = Files.exists(Paths.get("gameData.json"))
        val xmlFile = Files.exists(Paths.get("gameData.xml"))
        jsonFile | xmlFile shouldBe true
      }
    }
    "executing load" should {
      "load the game data from a file" in {
        val c = new Controller(GameState(GameData(attempts, solution)), colorFactory)
        c.addAttempt("red blue yellow green")
        c.save()
        val jsonFile = Files.exists(Paths.get("gameData.json"))
        val xmlFile = Files.exists(Paths.get("gameData.xml"))
        jsonFile | xmlFile shouldBe true
        c.setDifficulty("easy")
        c.gameState.gameData.getAttempt(9).userPickedColors(0).getColor shouldBe("          ")
        c.load()
        c.gameState.gameData.getAttempt(9).userPickedColors(0).getColor shouldBe("       red")
      }
    }
  }
}
