package mastermind

import com.google.inject.Guice
import mastermind.controllerComponent.ControllerInterface
import mastermind.view.{GUI, TUI}

import scala.io.StdIn.readLine

object MasterMind {

  def main(args: Array[String]): Unit = {
    println("Welcome to MasterMind!")

    val injector = Guice.createInjector(new MasterMindModule)
    val controller = injector.getInstance(classOf[ControllerInterface])


    if (args.length != 0) {
      val inputSwitch = args(0).toInt
      if (inputSwitch == 0) {
        println("Please select a difficulty with d easy/medium/mastermind")
        val tui = new TUI(controller)
        var input: String = ""
        do {
          input = readLine()
          tui.processInput(input)
        } while (input != "exit")
        println("Goodbye!")

      } else if (inputSwitch == 1) {
        val gui = new GUI(controller)
      }
    } else {
      println("Please provide a argument 0 or 1 for either TUI or GUI!")
    }

  }
}
