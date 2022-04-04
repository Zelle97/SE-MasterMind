package mastermind.controllerComponent.controllerBaseImpl

import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.model.gameDataComponent.GameDataInterface
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.util.Command

class AddCommand(gameData: GameDataInterface, attempt: AttemptInterface, controller: Controller) extends Command {

  private def updateGameData(index: Int): GameData =
    gameData.updateAttempt(index, attempt)

  private def updateGameData(index: Int, attempt: Attempt): GameData =
    gameData.updateAttempt(index, attempt)

  override def doStep(): Unit =
    controller.gameData = updateGameData(gameData.getAttemptSize()- controller.gameData.getTurn() - 1)
    controller.gameData.setTurn(controller.gameData.getTurn()+1)


  override def undoStep(): Unit =
    controller.gameData.setTurn(controller.gameData.getTurn()-1)
    controller.gameData = updateGameData(gameData.getAttemptSize() - controller.gameData.getTurn() - 1, Attempt())


  override def redoStep(): Unit =
    controller.gameData = updateGameData(gameData.getAttemptSize()- controller.gameData.getTurn() - 1)
    controller.gameData.setTurn(controller.gameData.getTurn()+1)

}
