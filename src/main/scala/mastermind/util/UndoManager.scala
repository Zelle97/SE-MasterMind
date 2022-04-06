package mastermind.util

import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doStep(command: Command): GameData = {
    undoStack = command :: undoStack
    command.doStep()
  }

  def undoStep(): Unit = {
    if(undoStack.nonEmpty) {
      undoStack match {
        case Nil =>
        case head :: stack =>
          head.undoStep()
          undoStack = stack
          redoStack = head :: redoStack
      }
    }
  }

  def redoStep(): Unit = {
    if(redoStack.nonEmpty) {
      redoStack match {
        case Nil =>
        case head :: stack =>
          head.redoStep()
          redoStack = stack
          undoStack = head :: undoStack
      }
    }
  }

  def clearList():Unit = {
    undoStack = Nil
    redoStack = Nil
  }
}
