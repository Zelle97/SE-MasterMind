package mastermind

import mastermind.controller.{ColorPicker, Controller}
import mastermind.model.GameData
import mastermind.view.TUI

import scala.io.StdIn.readLine

object MasterMind {

  val solution = ColorPicker().pickSolution()
  val controller = new Controller(GameData(solution = solution))
  val tui = new TUI(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    println("Welcome to MasterMind!")
    var input: String = ""

    do {
      input = readLine()
      tui.processInput(input)
    } while (input != "exit")

    println("Goodbye!")
  }

}
