package mastermind.util

trait Event

case class InGame() extends Event

case class Win() extends Event

case class GameOver() extends Event
