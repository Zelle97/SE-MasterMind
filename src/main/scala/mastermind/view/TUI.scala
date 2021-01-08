package mastermind.view

import mastermind.controllerComponent.{ControllerInterface, GameState}
import mastermind.controllerComponent.controllerBaseImpl.Controller
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
      case _ => controller.addAttempt(input)
    }
  }

  reactions += {
    case event: InGame => {
      GameState.handle(new InGame)
      println(controller.gameToString)
    }
    case event: Win => {
      GameState.handle(new Win)
      println(GameState.state)
    }
    case event: GameOver => {
      GameState.handle(new GameOver)
      println(GameState.state)
    }
  }

}
