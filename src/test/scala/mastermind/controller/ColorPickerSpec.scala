package mastermind.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ColorPickerSpec extends AnyWordSpec with Matchers {
  "A Color Picker" when {
    "created" should {
      "contain colors" in {
        ColorPicker().getAllColors().size shouldBe 8
      }
    }
    "colors are picked" should {
      "return random colors" in {
        ColorPicker().pickSolution() should contain atLeastOneElementOf ColorPicker().getAllColors()
      }
    }
  }
}
