package mastermind.model

import mastermind.model.colorComponent.colorBaseImpl.Color
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ColorSpec extends AnyWordSpec with Matchers {
  "A Color" when {
    "created" should {
      "only allow specified colors" in {
        Color("red").get.colorString shouldBe "red"
      }
      "created" should {
        "return a color formated color" in {
          Color("red").get.getColor shouldBe "       red"
        }
      }
      "compare to another Color" should {
        "return true" in {
          Color("red").get.equals(Color("red").get) shouldBe true
        }
      }
      "compare to String" should {
        "return false" in {
          Color("red").get.colorString.equals("blue") shouldBe false
        }
      }
      "compare to something else" should {
        "return false" in {
          Color("red").equals("blue") shouldBe false
        }
      }
    }
    "a specified color is created" should {
      "return that color" in {
        Color.apply("red").get.getColor shouldBe "       red"
      }
    }
    "all Colors" should {
      "be returned" in {
        Color.getAllColors.size shouldBe 8
      }
    }
    "a not specified color is created" should {
      "return an empty color" in {
        //Color.apply("asdf").getColor shouldBe "          "
      }
    }
  }
}
