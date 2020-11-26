package mastermind.view

import mastermind.controller.{ColorPicker, Controller}
import mastermind.model.GameData
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec with Matchers {
  "A TUI" when {
    val solution = ColorPicker().pickSolution()
    val controller = new Controller(GameData(solution = solution))
    val tui = new TUI(controller)
    "print out a TUI" should {
      "return true" in {
        tui.update shouldBe true
      }
    }
/*    "get a input" should {
      "add blue to attempt" in {
        tui.processInput("blue")
        tui.getController().gameData.attempts(9).userPickedColors(0).getColor() shouldBe "      blue"
      }
    }*/
  }

}
