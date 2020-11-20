package mastermind.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ColorSpec extends AnyWordSpec with Matchers {
  "A Color" when {
    "created" should {
      "only allow specified colors" in {
        Color("red").color shouldBe "red"
      }
      "created" should {
        "return a color formated color" in {
          Color("red").getColor() shouldBe "       red"
        }
      }
      "compare to another Color" should {
        "return true" in {
          Color("red").equals(Color("red")) shouldBe true
        }
      }
      "compare to String" should {
        "return false" in {
          Color("red").equals("red") shouldBe false
        }
      }

    }
  }
}
