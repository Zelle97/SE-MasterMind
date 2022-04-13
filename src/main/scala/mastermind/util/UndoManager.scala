package mastermind.util

import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doStep(command: Command): GameData = {
    undoStack = command :: undoStack
    command.doStep()
  }

  def undoStep(gameData: GameData): GameData = {
    if(undoStack.nonEmpty) {
      undoStack match {
        case Nil =>
        case head :: stack =>
          val newGameData = head.undoStep()
          undoStack = stack
          redoStack = head :: redoStack
          return newGameData
      }
    }
    gameData
  }

  def redoStep(gameData: GameData): GameData = {
    if(redoStack.nonEmpty) {
      redoStack match {
        case Nil =>
        case head :: stack =>
          val newGameData = head.redoStep()
          redoStack = stack
          undoStack = head :: undoStack
          return newGameData
      }
    }
    gameData
  }

  def clearList():Unit = {
    undoStack = Nil
    redoStack = Nil
  }
}
