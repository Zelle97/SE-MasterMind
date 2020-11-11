package mastermind

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AttemptSpec extends AnyWordSpec with Matchers{
  "A Attempt" when {
    "created" should {
      "contain 4 fields" in {
        Attempt().userPickedColors.size shouldBe(4)
      }
      "contain 4 empty fields" in {
        Attempt().userPickedColors(0) shouldBe "          "
        Attempt().userPickedColors(1) shouldBe "          "
        Attempt().userPickedColors(2) shouldBe "          "
        Attempt().userPickedColors(3) shouldBe "          "
      }
    }
    "updated" should {
      "update the field" in {
        Attempt().updateColor(0, "rot").userPickedColors(0) shouldBe "rot"
      }
    }
  }
}
