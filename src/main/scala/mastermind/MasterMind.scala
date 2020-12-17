package mastermind

import mastermind.controller.{ColorPicker, Controller}
import mastermind.model.Color.Shade
import mastermind.model.{Attempt, Color, DifficultyStrategy, GameData}
import mastermind.view.TUI

import scala.io.StdIn.readLine

object MasterMind {

  def difficultyMatcher(difficulty: String) = difficulty match {
    case "easy" => Some("easy")
    case "medium" => Some("medium")
    case "mastermind" => Some("mastermind")
    case _ => None
  }

  def main(args: Array[String]): Unit = {
    println("Welcome to MasterMind!")

    val difficultyInput: String = readLine("Player enter your difficulty level (easy, medium, mastermind): ")
    val difficulty = difficultyMatcher(difficultyInput)

    var attempts: Vector[Attempt] = Vector()

    if (difficulty.isDefined) {
      attempts = DifficultyStrategy.getAttempts(difficulty.get)
    } else {
      attempts = DifficultyStrategy.getAttempts("easy")
    }

    val solution: Vector[Shade] = ColorPicker().pickSolution()
    val controller = new Controller(GameData(attempts, solution))
    val tui = new TUI(controller)
    controller.notifyObservers

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
