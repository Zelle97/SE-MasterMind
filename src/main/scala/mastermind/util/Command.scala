package mastermind.util

import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData

trait Command {
  def doStep(): GameData
  def undoStep(): GameData
  def redoStep(): GameData
}
