package mastermind.controllerComponent

import mastermind.model.gameDataComponent.GameDataInterface

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  def setDifficulty(difficultyInput: String): Unit

  def addAttempt(input: String): Unit

  def getGameData(): GameDataInterface

  def gameToString: String

  def undo(): Unit

  def redo(): Unit

  def save(): Unit

  def load(): Unit

  def help(): Unit
}
