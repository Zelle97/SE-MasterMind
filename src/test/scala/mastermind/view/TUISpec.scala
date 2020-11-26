package mastermind.view

import mastermind.controller.{ColorPicker, Controller}
import mastermind.model.GameData
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec with Matchers {
  "The TUI" when {
    "created" should {
      val solution = ColorPicker().pickSolution()
      val controller = new Controller(GameData(solution = solution))
      "have a controller" in {
        new TUI(controller)
      }
    }
    val solution = ColorPicker().pickSolution()
    val controller = new Controller(GameData(solution = solution))
    val testTUI = new TUI(controller)
    "input exit is given" should {
      "exit the game" in {
        testTUI.processInput("exit")
      }
    }
    "any other input is given" should {
      "process the input" in {
        testTUI.processInput("a b c d")
      }
    }
    "print out a TUI" should {
      "return true" in {
        testTUI.update shouldBe true
      }
    }
  }
}
