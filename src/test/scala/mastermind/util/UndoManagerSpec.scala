package mastermind.util

import mastermind.controllerComponent.{DifficultyStrategy}
import mastermind.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class UndoManagerSpec extends AnyWordSpec with Matchers {
  "An UndoManager" should {
    val undoManager = new UndoManager
    val colorFactory = ColorFactory()
    val solution = colorFactory.pickSolution()
    val attempts = DifficultyStrategy.getAttempts("easy")
    "have a do, undo and redo" in {
      val command = new incrCommand
      command.state should be(0)
      undoManager.doStep(command)
      command.state should be(1)
      undoManager.undoStep(GameData(attempts, solution))
      command.state should be(0)
      undoManager.redoStep(GameData(attempts, solution))
      command.state should be(1)
    }
    "handle multiple undo steps correctly" in {
      val command = new incrCommand
      command.state should be(0)
      undoManager.doStep(command)
      command.state should be(1)
      undoManager.doStep(command)
      command.state should be(2)
      undoManager.undoStep(GameData(attempts, solution))
      command.state should be(1)
      undoManager.undoStep(GameData(attempts, solution))
      command.state should be(0)
      undoManager.redoStep(GameData(attempts, solution))
      command.state should be(1)
    }
    "clear both lists" in {
      undoManager.clearList()
    }
  }
}
