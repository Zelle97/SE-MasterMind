package mastermind.model

import mastermind.model.colorComponent.colorBaseImpl.Color
import mastermind.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ColorSpec extends AnyWordSpec with Matchers {
  "A Color" when {
    val colorFactory = ColorFactory()
    "created" should {
      "only allow specified colors" in {
        colorFactory.getColor("red").get.colorString shouldBe "red"
      }
      "created" should {
        "return a color formated color" in {
          colorFactory.getColor("red").get.getColor shouldBe "       red"
        }
      }
      "compare to another Color" should {
        "return true" in {
          colorFactory.getColor("red").get.equals(colorFactory.getColor("red").get) shouldBe true
        }
      }
      "compare to String" should {
        "return false" in {
          colorFactory.getColor("red").get.colorString.equals("blue") shouldBe false
        }
      }
      "compare to something else" should {
        "return false" in {
          colorFactory.getColor("red").equals("blue") shouldBe false
        }
      }
    }
    "a specified color is created" should {
      "return that color" in {
        colorFactory.getColor("red").get.getColor shouldBe "       red"
      }
    }
    "all Colors" should {
      "be returned" in {
        colorFactory.getAllColors().size shouldBe 8
      }
    }
    "a not specified color is created" should {
      "return an empty color" in {
        //Color.apply("asdf").getColor shouldBe "          "
      }
    }
  }
}
