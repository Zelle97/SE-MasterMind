package mastermind.controllerComponent

import mastermind.model.colorComponent.ColorFactoryInterface
import scala.util.Try
import scala.swing.Publisher

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
