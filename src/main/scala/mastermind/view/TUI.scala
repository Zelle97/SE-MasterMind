package mastermind.view

import mastermind.controller.Controller
import mastermind.util.Observer

class TUI(controller: Controller) extends Observer {

  controller.add(this)

  def processInput(input: String): Unit = {
    input match {
      case "z" => controller.undo
      case "y" => controller.redo
      case _ => controller.addAttempt(input)
    }
  }

  override def update: Boolean = {
    println(controller.gameToString); true
  }
}
