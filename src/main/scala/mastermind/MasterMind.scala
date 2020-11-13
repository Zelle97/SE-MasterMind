package mastermind

import scala.io.StdIn.readLine
import scala.util.control.Breaks

object MasterMind {

  def main(args: Array[String]): Unit = {
    println("Welcome to MasterMind!")
    var gameData = GameData()
    val gameboard = GameBoard(gameData, "").gameFieldString()
    println(gameboard.gamefield)

    val loop = new Breaks
    loop.breakable {
      var a = 9
      while (a != -1) {

        println("Pleas input 4 colors like this: color1 color2 color3 color4")
        val input = readLine()
        if (input.equals("exit")) {
          loop.break
        }

        val colors = input.split(" ").toVector
        val attempt = Attempt(colors.map(color => f"$color%10s"))

        //println(attempt.userPickedColors)
        gameData = gameData.updateAttempt(a, attempt)
        println(GameBoard(gameData, "").gameFieldString().gamefield)
        a = a - 1
      }
    }
    println("Goodbye!")
  }
}
