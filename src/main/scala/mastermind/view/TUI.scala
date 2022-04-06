package mastermind.view

import mastermind.controllerComponent.{ControllerInterface, GameState}
import mastermind.util.{GameOver, InGame, Win}

import scala.swing.Reactor
import scala.util.matching.Regex

class TUI(controller: ControllerInterface) extends Reactor {

  listenTo(controller)

  val difficultyPattern: Regex = "(d )(.*)".r

  def processInput(input: String): Unit = {
    input match {
      case "exit" =>
      case difficultyPattern(_, param) => controller.setDifficulty(param)
      case "z" => controller.undo()
      case "y" => controller.redo()
      case "s" => controller.save()
      case "l" => controller.load()
      case "h" => help()
      case _ => controller.addAttempt(input)
    }
  }

  def help(): Unit =
    print("Welcome to Mastermind!\n")
    print("These are the available commands:\n")
    print("color1 color2 color3 color4 -> Input colors\n")
    print("s -> Save your Game\n")
    print("l -> Load your saved Game\n")
    print("z -> Undo your last Step\n")
    print("y -> Redo your last Step\n")
    print("h -> Display this help\n")
    print("exit -> Exit the game\n")
    print("And here is described how mastermind works:\n")
    print("You are the codebreaker: Try to guess the pattern in order and color.\nThere are three different difficulties:\neasy -> 10 turns\nmedium -> 8 turns\nmastermind -> 7 turns\nEach guess is made by placing a row of code pegs on the decoding board.\nOnce placed, you are provided with some feedback on the right side of the row with your guess.\nGood Luck!!\n")


  reactions += {
    case event: InGame => {
      controller.gameState.handle(InGame(event.gameData))
      println(controller.gameState.gameData.toString())
    }
    case event: Win => {
      controller.gameState.handle(Win(event.gameData))
      println(controller.gameState.gameData.toString())
      println("Win! Play again by choosing a difficulty: d easy/medium/mastermind or type exit.")
    }
    case event: GameOver => {
      controller.gameState.handle(GameOver(event.gameData))
      println(controller.gameState.gameData.toString())
      println("Game Over! Play again by choosing a difficulty: d easy/medium/mastermind or type exit.")
    }
  }
}
