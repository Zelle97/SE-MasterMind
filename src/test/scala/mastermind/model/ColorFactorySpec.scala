package mastermind.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ColorFactorySpec extends AnyWordSpec with Matchers {
  "A ColorFactory" when {
    "a specified color is created" should {
      "return that color" in {
        ColorFactory.getColor("red").getColor() shouldBe "       red"
      }
    }
    "all Colors" should {
      "be returned" in {
        ColorFactory.getAllColors.size shouldBe 8
      }
    }
    "a not specified color is created" should {
      "return an empty color" in {
        ColorFactory.getColor("asdf").getColor() shouldBe "          "
      }
    }
  }
}
