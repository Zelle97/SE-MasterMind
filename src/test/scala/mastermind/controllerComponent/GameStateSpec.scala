package mastermind.controllerComponent

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import mastermind.util.{GameOver, InGame, Win}

class GameStateSpec extends AnyWordSpec with Matchers {
  "A GameState" when {
    "chosen GameOver" should {
      "return 'Game over'" in {
        GameState.handle(new GameOver()) shouldBe
          "!!Game over!! You lost the game!!!"
      }
    }
    "chosen Win" should {
      "return 'win'" in {
        GameState.handle(new Win()) shouldBe
          "!!Win!! You are a true MasterMind!!!"
      }
    }
    "chosen inGame" should {
      "return 'inGame'" in {
        GameState.handle(new InGame()) shouldBe
          "I am in Game"
      }
    }
  }
}
