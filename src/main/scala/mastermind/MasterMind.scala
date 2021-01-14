package mastermind

import com.google.inject.Guice
import mastermind.controllerComponent.{ControllerInterface, DifficultyStrategy}
import mastermind.controllerComponent.controllerBaseImpl.Controller
import mastermind.model.colorComponent.colorBaseImpl.Color
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.view.{GUI, TUI}

import scala.io.StdIn.readLine

object MasterMind {

  def main(args: Array[String]): Unit = {
    println("Welcome to MasterMind!")

    val injector = Guice.createInjector(new MasterMindModule)
    val controller = injector.getInstance(classOf[ControllerInterface])
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
