package mastermind.view

import mastermind.controller.{Controller, GameState}
import mastermind.util.{GameOver, InGame, Win}

import scala.swing.Reactor
import scala.util.matching.Regex

class TUI(controller: Controller) extends Reactor {

  listenTo(controller)

  val difficultyPattern: Regex = "(d )(.*)".r

  def processInput(input: String): Unit = {
    input match {
      case "exit" =>
      case difficultyPattern(_,param) => controller.setDifficulty(param)
      case "z" => controller.undo()
      case "y" => controller.redo()
      case _ => controller.addAttempt(input)
    }
  }

  reactions += {
    case event: InGame =>  println(controller.gameToString)
    case event: Win =>  println(GameState.win)
    case event: GameOver =>  println(GameState.gameOver)
  }

}
