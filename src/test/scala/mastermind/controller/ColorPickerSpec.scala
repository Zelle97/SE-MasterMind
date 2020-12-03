package mastermind.controller

import mastermind.model.{Color, ColorFactory}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ColorPickerSpec extends AnyWordSpec with Matchers {
  "A Color Picker" when {
    "a solution is picked" should {
      "pick a solution with 4 colors" in {
        ColorPicker().pickSolution().size shouldBe 4
      }
    }
    "a random color is picked" should {
      "return a color thats not already picked" in {
        val alreadyPicked = Vector(ColorFactory.getColor("red"),ColorFactory.getColor("blue"))
        alreadyPicked should not contain ColorPicker().pickRandomColor(alreadyPicked)
      }
    }
  }
}
