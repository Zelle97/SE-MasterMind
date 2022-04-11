package mastermind.controllerComponent

import mastermind.util.{GameOver, InGame, Win}
import mastermind.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData

class GameStateSpec extends AnyWordSpec with Matchers {
  // TODO Tests ergeben kein Sinn (Rückgabewert?)
  "A GameState" when {
    val colorFactory = ColorFactory()
    val solution = colorFactory.pickSolution()
    val attempts = DifficultyStrategy.getAttempts("easy")
    val gameState = GameState(GameData(attempts, solution))
    "chosen GameOver" should {
      "return 'Game over'" in {
        gameState.handle(GameOver(GameData(attempts, solution))) shouldBe
          GameState(GameData(attempts, solution))
      }
    }
    "chosen Win" should {
      "return 'win'" in {
        gameState.handle(Win(GameData(attempts, solution))) shouldBe
          GameState(GameData(attempts, solution))
      }
    }
    "chosen inGame" should {
      "return 'inGame'" in {
        gameState.handle(InGame(GameData(attempts, solution))) shouldBe
          GameState(GameData(attempts, solution))
      }
    }
  }
}
