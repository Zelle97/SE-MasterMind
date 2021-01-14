package mastermind.model

import mastermind.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.model.colorComponent.colorBaseImpl.Color
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AttemptSpec extends AnyWordSpec with Matchers {
  "A Attempt" when {
    "created" should {
      "contain 4 empty fields" in {
        Attempt().userPickedColors(0).getColor shouldBe "          "
        Attempt().userPickedColors(1).getColor shouldBe "          "
        Attempt().userPickedColors(2).getColor shouldBe "          "
        Attempt().userPickedColors(3).getColor shouldBe "          "
      }
      "contain the parameter fields" in {
        val testAttempt = Attempt(Vector(Color().apply("red").get, Color().apply("green").get, Color().apply("blue").get, Color().apply("yellow").get))
        testAttempt.userPickedColors(0).getColor shouldBe "       red"
        testAttempt.userPickedColors(1).getColor shouldBe "     green"
        testAttempt.userPickedColors(2).getColor shouldBe "      blue"
        testAttempt.userPickedColors(3).getColor shouldBe "    yellow"
      }
      "contain 4 fields" in {
        val testAttempt = Attempt(Vector(Color().apply("red").get, Color().apply("green").get, Color().apply("blue").get, Color().apply("yellow").get))
        testAttempt.getAllUserColors().size shouldBe 4
      }
      "get the correct number of colors" in {
        val testAttempt = Attempt(Vector(Color().apply("green").get, Color().apply("red").get, Color().apply("yellow").get, Color().apply("blue").get))
        testAttempt.getCorrectColors(Vector(Color().apply("red").get, Color().apply("green").get, Color().apply("blue").get, Color().apply("yellow").get)) shouldBe 4
      }
      "get the correct number of positions" in {
        val testAttempt = Attempt(Vector(Color().apply("red").get, Color().apply("green").get, Color().apply("blue").get, Color().apply("yellow").get))
        testAttempt.getCorrectColors(Vector(Color().apply("red").get, Color().apply("green").get, Color().apply("blue").get, Color().apply("yellow").get)) shouldBe 4
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
