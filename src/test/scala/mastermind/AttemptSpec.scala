package mastermind

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AttemptSpec extends AnyWordSpec with Matchers {
  "A Attempt" when {
    "created" should {
      "contain 4 fields" in {
        Attempt().userPickedColors.size shouldBe 4
      }
      "contain 4 empty fields" in {
        Attempt().userPickedColors(0) shouldBe "          "
        Attempt().userPickedColors(1) shouldBe "          "
        Attempt().userPickedColors(2) shouldBe "          "
        Attempt().userPickedColors(3) shouldBe "          "
      }
      "contain the parameter fields" in {
        val testAttempt = Attempt(Vector("test1", "test2", "test3", "test4"))
        testAttempt.userPickedColors(0) shouldBe "test1"
        testAttempt.userPickedColors(1) shouldBe "test2"
        testAttempt.userPickedColors(2) shouldBe "test3"
        testAttempt.userPickedColors(3) shouldBe "test4"
      }
    }
  }
}
