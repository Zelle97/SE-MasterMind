package mastermind.view

import mastermind.core.controller.Controller
import mastermind.core.GameState
import mastermind.core.DifficultyStrategy
import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import mastermind.core.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class TUISpec extends AnyWordSpec with Matchers {
  "The TUI" when {
    "created" should {
      "have a controller" in {
        new TUI()
      }
    }
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
    "the game" should {
      "get printed" in {
        testTUI.printGame("game")
      }
    }
    "the game begins" should {
      "print the welcome message" in {
        testTUI.welcome()
      }
    }
    "select a difficulty" should {
      "post the difficulty" in {
        testTUI.postDifficulty("easy")
      }
    }
    "select undo" should {
      "post an undo request" in {
        testTUI.postUndo()
      }
    }
    "select redo" should {
      "post an redo request" in {
        testTUI.postRedo()
      }
    }
    "select save" should {
      "post a save request" in {
        testTUI.postSave()
      }
    }
    "select load" should {
      "post a load request" in {
        testTUI.postLoad()
      }
    }
    "select a input" should {
      "post the input" in {
        testTUI.postInput("input")
      }
    }
    "the game is won" should {
      "should print the win message" in {
        testTUI.win("game")
      }
    }
    "the game is gameOver" should {
      "should print the gameOver message" in {
        testTUI.gameOver("game")
      }
    }
    "the game" should {
      val solution = Vector.fill(1)(ColorView("green"))
      val attempts = Vector.fill(1)(AttemptView(solution))
      "should react to gameState" in {
        testTUI.reactToGameState(GameStateView(GameDataView(attempts, solution, 0), "", "Win"))
      }
    }
    "help is called" should {
      "return the help message" in {
        """
          |Welcome to Mastermind!
          |These are the available commands:
          |color1 color2 color3 color4 -> Input colors
          |s -> Save your Game
          |l -> Load your saved Game
          |z -> Undo your last Step
          |y -> Redo your last Step
          |h -> Display this help
          |exit -> Exit the game
          |And here is described how mastermind works:
          |You are the codebreaker: Try to guess the pattern in order and color.
          |There are three different difficulties:
          |easy -> 10 turns
          |medium -> 8 turns
          |mastermind -> 7 turns
          |Each guess is made by placing a row of code pegs on the decoding board.
          |Once placed, you are provided with some feedback on the right side of the row with your guess.
          |Good Luck!!
          |""".stripMargin shouldBe testTUI.help()
      }
    }

  }
}
