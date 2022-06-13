package mastermind.core.controller

import mastermind.core.GameState
import mastermind.core.model.attemptComponent.AttemptInterface
import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.core.util.Command

class AddCommand(gameState: GameState, attempt: AttemptInterface) extends Command {
  override def doStep(): GameData =
    gameState.gameData.copy(attempts = gameState.gameData.updateAttempt(gameState.gameData.getCurrentTurn, attempt).attempts, turn = gameState.gameData.turn + 1)
  override def undoStep(): GameData =
    gameState.gameData.copy(attempts = gameState.gameData.updateAttempt(gameState.gameData.getCurrentTurn+1, Attempt()).attempts, turn = gameState.gameData.turn - 1)
  override def redoStep(): GameData =
    gameState.gameData.copy(attempts = gameState.gameData.updateAttempt(gameState.gameData.getCurrentTurn, attempt).attempts, turn = gameState.gameData.turn + 1)
}
