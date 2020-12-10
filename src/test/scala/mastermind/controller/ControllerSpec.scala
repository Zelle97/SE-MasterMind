package mastermind.controller



import mastermind.model.{DifficultyStrategy, GameData}
import mastermind.util.GameOver
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
    "adding an Attempt " should {
      val c = new Controller(GameData(attempts, solution))
      "increase the turn" in {
        val before = c.turn
        c.addAttempt("red")
        before+1 shouldBe c.turn
      }
    }
    "adding an Attempt and turns are over" should {
      val c = new Controller(GameData(attempts, solution))
      "set the GameStatus on GameOver" in {
        c.turn = c.gameData.attempts.size -1
        c.addAttempt("red green yellow blue")
        GameState.state shouldBe "!!Game over!! You are a loser!!!"
      }
    }
    "adding an Attempt and game is Won" should {
      val c = new Controller(GameData(attempts, solution))
      "set the GameStatus on GameOver" in {
        val solutionAttempt = solution(0).colorString + " " + solution(1).colorString + " " + solution(2).colorString + " " + solution(3).colorString
        c.addAttempt(solutionAttempt)
        GameState.state shouldBe "!!Win!! You are a true Mastermind!!!"
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
      "redo the previous attempt" in {
        val c = new Controller(GameData(attempts, solution))
        c.addAttempt("red blue yellow green")
        val before = c.turn
        c.undo()
        c.redo()
        print(c.turn)
        print(c.gameData.attempts)
        c.gameData.attempts(c.gameData.attempts.size - c.turn).userPickedColors(0).getColor shouldBe "       red"
        before shouldBe c.turn
      }
    }
  }
}
