package mastermind

import mastermind.controller.ColorPicker
import mastermind.model.Color
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ColorSpec extends AnyWordSpec with Matchers {
  "A Color" when {
    "created" should {
      "only allow specified colors" in {
        Color("red").color shouldBe "red"
      }
    }
  }
}
