package mastermind.controller



import mastermind.model.{DifficultyStrategy, GameData}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when {
    val solution = ColorPicker().pickSolution()
    val attempts = DifficultyStrategy.getAttempts("easy")
    "created" should {
      "printed a Gameboard" in {
        new Controller(GameData(attempts, solution)).gameToString shouldBe "\t\t\t\tMaster Mind\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
    "adding an Attempt" should {
      val c = new Controller(GameData(attempts, solution))
      "add a attempt" in {
        c.addAttempt("red")
        c.gameData.attempts(9).userPickedColors(0).getColor shouldBe "       red"
      }
      "increase the turn" in {
        val before = c.turn
        c.addAttempt("red")
        before+1 shouldBe c.turn
      }
    }
    "executing undo" should {
      val c = new Controller(GameData(attempts, solution))
      c.addAttempt("red")
      "undo the attempt" in {
        val before = c.turn
        c.undo()
        c.gameData.attempts(9).userPickedColors(0).getColor shouldBe "          "
        before-1 shouldBe c.turn
      }
    }
    "executing redo" should {
      val c = new Controller(GameData(attempts, solution))
      c.addAttempt("red")
      "redo the previous attempt" in {
        val before = c.turn
        c.redo()
        c.gameData.attempts(c.turn).userPickedColors(0).getColor shouldBe "          "
        before shouldBe c.turn
      }
    }
  }
}
