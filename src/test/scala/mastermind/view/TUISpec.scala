package mastermind.view

import mastermind.core.controller.Controller
import mastermind.core.GameState
import mastermind.core.DifficultyStrategy
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import mastermind.core.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class TUISpec extends AnyWordSpec with Matchers {
  "The TUI" when {
    val colorFactory = ColorFactory()
    val attempts = DifficultyStrategy.getAttempts("easy")
    val color = colorFactory
    val solution = colorFactory.pickSolution()
    "created" should {
      "have a controller" in {
        new TUI()
      }
    }
    val controller = new Controller(GameState(new GameData(attempts, solution)), color)
    val testTUI = new TUI()
    "input exit is given" should {
      "exit the game" in {
        //testTUI.processInput("exit")
      }
    }
/*    "input z is given" should {
            "undo the last action" in {
        controller.addAttempt("red blue green yellow")
        testTUI.processInput("z")
        controller.gameState.gameData.getAttempt(9).userPickedColors(0).getColor shouldBe "          "
      }
    }*/
/*    "input y is given" should {
      "redo the last action" in {
        testTUI.processInput("y")
        controller.gameState.gameData.getAttempt(9).userPickedColors(0).getColor shouldBe "       red"
      }
    }*/
      "any other input is given" should {
        "process the input" in {
          testTUI.processInput("a b c d")
        }
      }

  }
}
