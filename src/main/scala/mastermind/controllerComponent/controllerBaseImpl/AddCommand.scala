package mastermind.controllerComponent.controllerBaseImpl

import mastermind.controllerComponent.GameState
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.util.Command

class AddCommand(gameState: GameState, attempt: AttemptInterface) extends Command {

  override def doStep(): GameData =
    gameState.state.copy(attempts = gameState.state.updateAttempt(gameState.state.getCurrentTurn, attempt).attempts, turn = gameState.state.turn + 1)
  override def undoStep(): GameData =
    gameState.state.copy(attempts = gameState.state.updateAttempt(gameState.state.getCurrentTurn-1, Attempt()).attempts, turn = gameState.state.turn - 1)
  override def redoStep(): GameData =
    gameState.state.copy(attempts = gameState.state.updateAttempt(gameState.state.getCurrentTurn, attempt).attempts, turn = gameState.state.turn + 1)

}
