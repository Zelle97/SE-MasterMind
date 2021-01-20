package mastermind.controllerComponent




import java.nio.file.{Files, Paths}

import mastermind.controllerComponent.controllerBaseImpl.Controller
import mastermind.model.colorComponent.colorBaseImpl.Color
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.util.GameOver
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when {
    val color = Color()
    val solution = color.pickSolution()
    val attempts = DifficultyStrategy.getAttempts("easy")
    "created" should {
      "printed a Gameboard" in {
        new Controller(GameData(attempts, solution), color).gameToString shouldBe "\t\t\t\tMaster Mind\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
    "adding an Attempt" should {
      val c = new Controller(GameData(attempts, solution), color)
      "add a attempt" in {
        c.addAttempt("red blue yellow green")
        c.gameData.getAttempt(9).getUserPickedColor(0).getColor shouldBe "       red"
      }
      "increase the turn" in {
        val before = c.gameData.getTurn()
        c.addAttempt("red blue yellow green")
        before+1 shouldBe c.gameData.getTurn()
      }
    }
    "adding an Attempt " should {
      val c = new Controller(GameData(attempts, solution), color)
      "increase the turn" in {
        val before = c.gameData.getTurn()
        c.addAttempt("red blue yellow green")
        before+1 shouldBe c.gameData.getTurn()
      }
    }
    "changing the difficulty" should {
      val c = new Controller(GameData(attempts, solution), color)
      "set the GameData size to mastermind" in {
        c.setDifficulty("mastermind")
        c.gameData.getAttemptSize() shouldBe 7
      }
      "set the GameData size to medium" in {
        c.setDifficulty("medium")
        c.gameData.getAttemptSize() shouldBe 8
      }
      "set the GameData size to easy" in {
        c.setDifficulty("easy")
        c.gameData.getAttemptSize() shouldBe 10
      }
    }
    "executing undo" should {
      val c = new Controller(GameData(attempts, solution), color)
      c.addAttempt("red blue yellow green")
      "undo the attempt" in {
        val before = c.gameData.getTurn()
        c.undo()
        c.gameData.getAttempt(9).getUserPickedColor(0).getColor shouldBe "          "
        before-1 shouldBe c.gameData.getTurn()
      }
    }
    "executing redo" should {
      "redo the previous attempt" in {
        val c = new Controller(GameData(attempts, solution), color)
        c.addAttempt("red blue yellow green")
        val before = c.gameData.getTurn()
        c.undo()
        c.redo()
        print(c.gameData.getTurn())
        print(c.gameData.getAllAttempts())
        c.gameData.getAttempt(c.gameData.getAttemptSize() - c.gameData.getTurn()).getUserPickedColor(0).getColor shouldBe "       red"
        before shouldBe c.gameData.getTurn()
      }
    }
    "executing save" should {
      "save the game as a file" in {
        val c = new Controller(GameData(attempts, solution), color)
        c.save()
        val jsonFile = Files.exists(Paths.get("gameData.json"))
        val xmlFile = Files.exists(Paths.get("gameData.xml"))
        jsonFile | xmlFile shouldBe true
      }
    }
    "executing load" should {
      "load the game data from a file" in {
        val c = new Controller(GameData(attempts, solution), color)
        c.addAttempt("red blue yellow green")
        c.save()
        val jsonFile = Files.exists(Paths.get("gameData.json"))
        val xmlFile = Files.exists(Paths.get("gameData.xml"))
        jsonFile | xmlFile shouldBe true
        c.setDifficulty("easy")
        c.getGameData().getAttempt(9).getUserPickedColor(0).getColor shouldBe("          ")
        c.load()
        c.getGameData().getAttempt(9).getUserPickedColor(0).getColor shouldBe("       red")
      }
    }
  }
}
