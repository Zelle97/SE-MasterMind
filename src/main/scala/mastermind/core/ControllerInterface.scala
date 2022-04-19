package mastermind.core

import mastermind.core.model.colorComponent.ColorFactoryInterface

import scala.swing.Publisher
import scala.util.Try

trait ControllerInterface() extends Publisher {
  val gameState: GameState
  val colorFactory: ColorFactoryInterface

  def setDifficulty(difficultyInput: String): Try[String]

  def addAttempt(input: String): Try[String]

  def undo(): Unit

  def redo(): Unit

  def save(): Unit

  def load(): Unit
}
