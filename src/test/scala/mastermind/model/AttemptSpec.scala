package mastermind.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AttemptSpec extends AnyWordSpec with Matchers {
  "A Attempt" when {
    "created" should {
      "contain 4 fields" in {
        Attempt().userPickedColors.size shouldBe 4
      }
      "contain 4 empty fields" in {
        Attempt().userPickedColors(0).getColor shouldBe "          "
        Attempt().userPickedColors(1).getColor shouldBe "          "
        Attempt().userPickedColors(2).getColor shouldBe "          "
        Attempt().userPickedColors(3).getColor shouldBe "          "
      }
      "contain the parameter fields" in {
        val testAttempt = Attempt(Vector(Color("red").get, Color("green").get, Color("blue").get, Color("yellow").get))
        testAttempt.userPickedColors(0).getColor shouldBe "       red"
        testAttempt.userPickedColors(1).getColor shouldBe "     green"
        testAttempt.userPickedColors(2).getColor shouldBe "      blue"
        testAttempt.userPickedColors(3).getColor shouldBe "    yellow"
      }
    }
    "created with invalid colors" should {
      "contain the empty fields" in {
        //val testAttempt = Attempt(Vector(Color("test1"), Color("test2"), Color("test3"), Color("test4")))
        //testAttempt.userPickedColors(0).getColor shouldBe "          "
        //testAttempt.userPickedColors(1).getColor shouldBe "          "
        //testAttempt.userPickedColors(2).getColor shouldBe "          "
        //testAttempt.userPickedColors(3).getColor shouldBe "          "
      }
    }
  }
}
