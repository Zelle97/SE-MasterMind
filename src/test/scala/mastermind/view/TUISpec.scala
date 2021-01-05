package mastermind.view

import mastermind.controller.{ColorPicker, Controller}
import mastermind.model.{DifficultyStrategy, GameData}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class TUISpec extends AnyWordSpec with Matchers {
  "The TUI" when {
    val attempts = DifficultyStrategy.getAttempts("easy")
    val solution = ColorPicker().pickSolution()
    "created" should {

      val controller = new Controller(GameData(attempts, solution))
      "have a controller" in {
        new TUI(controller)
      }
    }
    val controller = new Controller(GameData(attempts, solution))
    val testTUI = new TUI(controller)
    "input exit is given" should {
      "exit the game" in {
        //testTUI.processInput("exit")
      }
    }
    "input z is given" should {
      "undo the last action" in {
        controller.addAttempt("red blue green yellow")
        testTUI.processInput("z")
        controller.gameData.getAttempt(9).getUserPickedColor(0).getColor shouldBe "          "
      }
    }
    "input y is given" should {
      "redo the last action" in {
        testTUI.processInput("y")
        controller.gameData.getAttempt(9).getUserPickedColor(0).getColor shouldBe "       red"
      }
    }
    "any other input is given" should {
      "process the input" in {
        testTUI.processInput("a b c d")
      }
    }
  }
}
