package mastermind.controller

import mastermind.model.{Attempt, AttemptInterface, GameData, GameDataInterface}
import mastermind.util.Command

class AddCommand(gameData: GameDataInterface, attempt: AttemptInterface, controller: Controller) extends Command {

  private def updateGameData(index: Int): GameData = {
    gameData.updateAttempt(index, attempt)
  }

  private def updateGameData(index: Int, attempt: Attempt): GameData = {
    gameData.updateAttempt(index, attempt)
  }

  override def doStep(): Unit = {
    controller.gameData = updateGameData(gameData.getAttemptSize()- controller.turn - 1)
    controller.turn = controller.turn + 1
  }

  override def undoStep(): Unit = {
    controller.turn = controller.turn - 1
    controller.gameData = updateGameData(gameData.getAttemptSize() - controller.turn - 1, Attempt())
  }

  override def redoStep(): Unit = {
    controller.gameData = updateGameData(gameData.getAttemptSize()- controller.turn - 1)
    controller.turn = controller.turn + 1
  }
}
