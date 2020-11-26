package mastermind.view

import mastermind.controller.Controller
import mastermind.util.Observer

class TUI(controller: Controller) extends Observer {

  controller.add(this)

  def processInput(input: String): Unit = {
    input match {
      case "exit" => controller.quitGame()
      case _ => controller.addAttempt(input)
    }
  }

  def getController(): Controller = {
    controller
  }

  override def update: Boolean = {println(controller.gameToString()); true}
}
