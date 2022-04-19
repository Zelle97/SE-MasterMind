package mastermind.model

import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import mastermind.core.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AttemptSpec extends AnyWordSpec with Matchers {
  "A Attempt" when {
    val colorFactory = ColorFactory()
    "created" should {
      "contain 4 empty fields" in {
        Attempt().userPickedColors(0).getColor shouldBe "          "
        Attempt().userPickedColors(1).getColor shouldBe "          "
        Attempt().userPickedColors(2).getColor shouldBe "          "
        Attempt().userPickedColors(3).getColor shouldBe "          "
      }
      "contain the parameter fields" in {
        val testAttempt = Attempt(Vector(colorFactory.getColor("red").get, colorFactory.getColor("green").get, colorFactory.getColor("blue").get, colorFactory.getColor("yellow").get))
        testAttempt.userPickedColors(0).getColor shouldBe "       red"
        testAttempt.userPickedColors(1).getColor shouldBe "     green"
        testAttempt.userPickedColors(2).getColor shouldBe "      blue"
        testAttempt.userPickedColors(3).getColor shouldBe "    yellow"
      }
      "contain 4 fields" in {
        val testAttempt = Attempt(Vector(colorFactory.getColor("red").get, colorFactory.getColor("green").get, colorFactory.getColor("blue").get, colorFactory.getColor("yellow").get))
        testAttempt.userPickedColors.size shouldBe 4
      }
      "get the correct number of colors" in {
        val testAttempt = Attempt(Vector(colorFactory.getColor("green").get, colorFactory.getColor("red").get, colorFactory.getColor("yellow").get, colorFactory.getColor("blue").get))
        testAttempt.getCorrectColors(Vector(colorFactory.getColor("red").get, colorFactory.getColor("green").get, colorFactory.getColor("blue").get, colorFactory.getColor("yellow").get)) shouldBe 4
      }
      "get the correct number of positions" in {
        val testAttempt = Attempt(Vector(colorFactory.getColor("red").get, colorFactory.getColor("green").get, colorFactory.getColor("blue").get, colorFactory.getColor("yellow").get))
        testAttempt.getCorrectColors(Vector(colorFactory.getColor("red").get, colorFactory.getColor("green").get, colorFactory.getColor("blue").get, colorFactory.getColor("yellow").get)) shouldBe 4
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
