package mastermind.controllerComponent

import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DifficultyStrategySpec extends AnyWordSpec with Matchers {
  "A Strategy" when {
    "chosen nothing" should {
      "return 10 Attempst" in{
        DifficultyStrategy.getAttempts() shouldBe
          Vector[Attempt](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())
      }
    }
    "chosen easy" should {
      "return 10 Attempst" in{
        DifficultyStrategy.getAttempts("easy") shouldBe
          Vector[Attempt](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())
      }
    }
    "chosen medium" should {
      "return 8 Attempst" in {
        DifficultyStrategy.getAttempts("medium") shouldBe
          Vector[Attempt](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())
      }
    }
    "chosen mastermind" should {
      "return 7 Attempst" in {
        DifficultyStrategy.getAttempts("mastermind") shouldBe
          Vector[Attempt](Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt(), Attempt())
      }
    }
  }
}
