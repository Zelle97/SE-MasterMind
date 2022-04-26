package mastermind.view

import mastermind.core.GameState
import mastermind.core.util.{GameOver, InGame, Win}

import scala.swing.Reactor
import scala.util.{Failure, Success}
import scala.util.matching.Regex

class TUI() {

  val difficultyPattern: Regex = "(d )(.*)".r

/*
  def processInput(input: String): Unit = {
    input match {
      case "exit" =>  println("Goodbye!")
      case difficultyPattern(_, param) => controller.setDifficulty(param) match {
        case Failure(exception) => println(exception.getMessage)
        case Success(_) =>
      }
      case "z" => controller.undo()
      case "y" => controller.redo()
      case "s" => controller.save()
      case "l" => controller.load()
      case "h" => println(help())
      case _ => controller.addAttempt(input) match {
        case Failure(exception) => println(exception.getMessage)
        case Success(_) =>
      }
    }
  }
*/
  def processInput(input: String): Unit = {
    input match {
      case "h" => println(help());
    }
  }



  def welcome(): Unit =
    println("""
      |Welcome to Mastermind!
      |Please select a difficulty with d easy/medium/mastermind
      |""".stripMargin)

  def help(): String =
    """
    |Welcome to Mastermind!
    |These are the available commands:
    |color1 color2 color3 color4 -> Input colors
    |s -> Save your Game
    |l -> Load your saved Game
    |z -> Undo your last Step
    |y -> Redo your last Step
    |h -> Display this help
    |exit -> Exit the game
    |And here is described how mastermind works:
    |You are the codebreaker: Try to guess the pattern in order and color.
    |There are three different difficulties:
    |easy -> 10 turns
    |medium -> 8 turns
    |mastermind -> 7 turns
    |Each guess is made by placing a row of code pegs on the decoding board.
    |Once placed, you are provided with some feedback on the right side of the row with your guess.
    |Good Luck!!
    |""".stripMargin


  def gameOver(gameString : String): Unit =
    printGame(gameString)
    println("Game Over! Play again by choosing a difficulty: d easy/medium/mastermind or type exit.")

  def win(gameString : String): Unit =
    printGame(gameString)
    println("Win! Play again by choosing a difficulty: d easy/medium/mastermind or type exit.")

  def printGame(gameString : String) : Unit =
    println(gameString)

  def reactToGameState(gameStateView: GameStateView): Unit = {
    gameStateView.state match {
      case "InGame" => printGame(gameStateView.gameString)
      case "Win" => win(gameStateView.gameString)
      case "GameOver" => gameOver(gameStateView.gameString)

    }
  }
}
