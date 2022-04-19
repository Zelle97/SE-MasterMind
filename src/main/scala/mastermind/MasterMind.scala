package mastermind

import com.google.inject.Guice
import mastermind.core.ControllerInterface
import mastermind.core.DifficultyStrategy
import mastermind.core.controllerBaseImpl.Controller
import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.view.{GUI, TUI}

import scala.io.StdIn.readLine

object MasterMind {

  @main def tui(): Unit =
    val controller = mainRoutine()
    runTUI(controller)

  @main def gui(): Unit =
    val controller = mainRoutine()
    runGUI(controller)

  @main def both(): Unit =
    val controller = mainRoutine()
    runGUI(controller)
    runTUI(controller)

  def mainRoutine(): ControllerInterface =
    val injector = Guice.createInjector(new MasterMindModule)
    injector.getInstance(classOf[ControllerInterface])

  def runGUI(controller: ControllerInterface) =
    val gui = new GUI(controller)

  def runTUI(controller: ControllerInterface) =
    val tui = new TUI(controller)
    tui.welcome()
    var input: String = ""
    while
      input = readLine()
      tui.processInput(input)
      input != "exit"
    do ()

}
