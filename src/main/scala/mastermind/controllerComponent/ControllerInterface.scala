package mastermind.controllerComponent

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  def setDifficulty(difficultyInput: String):Unit
  def addAttempt(input:String):Unit
  def gameToString:String
  def undo():Unit
  def redo():Unit

}
