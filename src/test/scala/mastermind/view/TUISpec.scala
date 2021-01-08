package mastermind.view

import mastermind.controller.{Controller, DifficultyStrategy, GameState}
import mastermind.model.{Color, GameData}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class TUISpec extends AnyWordSpec with Matchers {
  "The TUI" when {
    val attempts = DifficultyStrategy.getAttempts("easy")
    val color = Color
    val solution = color.pickSolution()
    "created" should {

      val controller = new Controller(GameData(attempts, solution),color)
      "have a controller" in {
        new TUI(controller)
      }
    }
    val controller = new Controller(GameData(attempts, solution),color)
    val testTUI = new TUI(controller)
    "input exit is given" should {
      "exit the game" in {
        //testTUI.processInput("exit")
      }
    }
    "input z is given" should {
      "undo the last action" in {
        controller.addAttempt("red blue green yellow")
        testTUI.processInput("z")
        controller.gameData.getAttempt(9).getUserPickedColor(0).getColor shouldBe "          "
      }
    }
    "input y is given" should {
      "redo the last action" in {
        testTUI.processInput("y")
        controller.gameData.getAttempt(9).getUserPickedColor(0).getColor shouldBe "       red"
      }
    }
    "any other input is given" should {
      "process the input" in {
        testTUI.processInput("a b c d")
      }
    }
    "adding an Attempt and turns are over" should {
      "set the GameStatus on GameOver" in {
        controller.turn = controller.gameData.getAttemptSize()-1
        controller.addAttempt("red green yellow blue")
        GameState.state shouldBe "!!Game over!! You are a loser!!!"
      }
    }
    "adding an Attempt and game is Won" should {
      "set the GameStatus on GameOver" in {
        controller.turn = 9
        val solutionAttempt = solution(0).colorString + " " + solution(1).colorString + " " + solution(2).colorString + " " + solution(3).colorString
        controller.addAttempt(solutionAttempt)
        GameState.state shouldBe "!!Win!! You are a true Mastermind!!!"
      }
    }
  }
}
