package mastermind.controllerComponent

import mastermind.model.colorComponent.ColorFactoryInterface

import scala.swing.Publisher

trait ControllerInterface() extends Publisher {
  val state: GameState
  val colorFactory: ColorFactoryInterface
  def setDifficulty(difficultyInput: String): Unit
  def addAttempt(input: String): Unit
  def gameToString: String
  def undo(): Unit
  def redo(): Unit
  def save(): Unit
  def load(): Unit
  def help(): Unit
}
