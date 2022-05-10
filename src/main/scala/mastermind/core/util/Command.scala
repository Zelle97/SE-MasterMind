package mastermind.core.util

import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData

trait Command {
  def doStep(): GameData
  def undoStep(): GameData
  def redoStep(): GameData
}
