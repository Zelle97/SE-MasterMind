package mastermind

import mastermind.controller.{ColorPicker, Controller}
import mastermind.model.Color.Shade
import mastermind.model.{Attempt, DifficultyStrategy, GameData}
import mastermind.view.TUI

import scala.io.StdIn.readLine

object MasterMind {
  println("Welcome to MasterMind!")
  val difficulty: String = readLine("Player enter your difficulty level (easy, medium, mastermind): ")
  val attempts: Vector[Attempt] = DifficultyStrategy.getAttempts(difficulty)

  val solution: Vector[Shade] = ColorPicker().pickSolution()
  val controller = new Controller(GameData(attempts, solution))
  val tui = new TUI(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = ""
    if(args.length != 0) {
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
