package mastermind.controller

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  def addAttempt(input:String):Unit
  def undo():Unit
  def redo():Unit

}
