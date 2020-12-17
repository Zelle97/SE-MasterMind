package mastermind.view

import mastermind.controller.Controller
import mastermind.util.Observer

import scala.util.matching.Regex

class TUI(controller: Controller) extends Observer {

  //controller.add(this)

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

  override def update: Boolean = {
    println(controller.gameToString); true
  }
}
