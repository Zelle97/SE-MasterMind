package mastermind.controller

import mastermind.model.Color
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ShadePickerSpec extends AnyWordSpec with Matchers {
  "A Color Picker" when {
    "a solution is picked" should {
      "pick a solution with 4 colors" in {
        ColorPicker().pickSolution().size shouldBe 4
      }
    }
    "a random color is picked" should {
      "return a color thats not already picked" in {
        val alreadyPicked = Vector(Color.apply("red"),Color.apply("blue"))
        alreadyPicked should not contain ColorPicker().pickRandomColor(alreadyPicked)
      }
    }
  }
}
