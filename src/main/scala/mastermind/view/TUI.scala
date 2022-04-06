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
      case "h" => controller.help()
      case _ => controller.addAttempt(input)
    }
  }
  reactions += {
    case event: InGame => {
      controller.state.handle(InGame(event.gameData))
      println(controller.gameToString)
    }
    case event: Win => {
      controller.state.handle(Win(event.gameData))
      println(controller.gameToString)
      println("Win! Play again by choosing a difficulty: d easy/medium/mastermind or type exit.")
    }
    case event: GameOver => {
      controller.state.handle(GameOver(event.gameData))
      println(controller.gameToString)
      println("Game Over! Play again by choosing a difficulty: d easy/medium/mastermind or type exit.")
    }
  }
}
