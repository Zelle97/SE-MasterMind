package mastermind

import mastermind.controller.{ColorPicker, Controller}
import mastermind.model.{DifficultyStrategy, GameData}
import mastermind.view.{GUI, TUI}

import scala.io.StdIn.readLine

object MasterMind {

  def main(args: Array[String]): Unit = {
    println("Welcome to MasterMind!")

    val controller = new Controller(GameData(DifficultyStrategy.getAttempts(), ColorPicker().pickSolution()))
    val tui = new TUI(controller)
    val gui = new GUI(controller)

    var input: String = ""
    if (args.length != 0) {
      input = args(0)
      tui.processInput(input)
    }
    else do {
      input = readLine()
      tui.processInput(input)
    } while (input != "exit")

    println("Goodbye!")
  }
}
