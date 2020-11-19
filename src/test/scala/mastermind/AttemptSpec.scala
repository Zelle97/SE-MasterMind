package mastermind

import mastermind.model.{Attempt, Color}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AttemptSpec extends AnyWordSpec with Matchers {
  "A Attempt" when {
    "created" should {
      "contain 4 fields" in {
        Attempt().userPickedColors.size shouldBe 4
      }
      "contain 4 empty fields" in {
        Attempt().userPickedColors(0).color shouldBe "          "
        Attempt().userPickedColors(1).color shouldBe "          "
        Attempt().userPickedColors(2).color shouldBe "          "
        Attempt().userPickedColors(3).color shouldBe "          "
      }
      "contain the parameter fields" in {
        val testAttempt = Attempt(Vector(Color("test1"),Color("test2"), Color("test3"), Color("test4")))
        testAttempt.userPickedColors(0).color shouldBe "test1"
        testAttempt.userPickedColors(1).color shouldBe "test2"
        testAttempt.userPickedColors(2).color shouldBe "test3"
        testAttempt.userPickedColors(3).color shouldBe "test4"
      }
    }
  }
}
