package mastermind.util

import mastermind.controllerComponent.DifficultyStrategy
import mastermind.core.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class incrCommand extends Command {
  var state: Int = 0
  val colorFactory = ColorFactory()
  val solution = colorFactory.pickSolution()
  val attempts = DifficultyStrategy.getAttempts("easy")

  override def doStep() : GameData =
    state += 1
    return GameData(attempts, solution)


  override def undoStep(): GameData =
    state -= 1
    GameData(attempts, solution)

  override def redoStep(): GameData =
    state += 1
    GameData(attempts, solution)
}

class CommandSpec extends AnyWordSpec with Matchers {
  "A Command" should {
    "have a do step" in {
      val command = new incrCommand
      command.state should be(0)
      command.doStep()
      command.state should be(1)
      command.doStep()
      command.state should be(2)
    }
    "have an undo step" in {
      val command = new incrCommand
      command.state should be(0)
      command.doStep()
      command.state should be(1)
      command.undoStep()
      command.state should be(0)
    }
    "have a redo step" in {
      val command = new incrCommand
      command.state should be(0)
      command.doStep()
      command.state should be(1)
      command.undoStep()
      command.state should be(0)
      command.redoStep()
      command.state should be(1)
    }
  }
}
