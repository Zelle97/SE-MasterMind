package mastermind

import mastermind.controller.Controller
import mastermind.model.GameData
import mastermind.view.TUI

import scala.io.StdIn.readLine

object MasterMind {

  val controller = new Controller(GameData())
  val tui = new TUI(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    println("Welcome to MasterMind!")
    var input: String = ""

    do {
      input = readLine()
      tui.processInput(input)
    } while (input != "q")

    println("Goodbye!")
  }

}
