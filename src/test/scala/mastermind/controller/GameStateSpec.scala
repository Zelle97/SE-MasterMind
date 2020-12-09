package mastermind.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import mastermind.util.{Event, GameOver, InGame, Win}

class GameStateSpec extends AnyWordSpec with Matchers {
  "A GameState" when {
    "chosen GameOver" should {
      "return 'Game over'" in {
        GameState.handle(GameOver()) shouldBe
          "!!Game over!! You are a looser!!!"
      }
    }
    "chosen Win" should {
      "return 'win'" in {
        GameState.handle(Win()) shouldBe
          "!!Win!! You are a true Mastermind!!!"
      }
    }
    "chosen inGame" should {
      "return 'inGame'" in {
        GameState.handle(InGame()) shouldBe
          "I am in Game"
      }
    }
  }
}
