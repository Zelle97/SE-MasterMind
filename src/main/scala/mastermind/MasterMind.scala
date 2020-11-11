package mastermind

import scala.io.StdIn.readLine
import scala.util.control.Breaks

object MasterMind {

  def main(args: Array[String]): Unit = {
    println("Welcome to MasterMind!")
    val gameboardb = GameBoard(GameData().initializeEmpty(), "").gameFieldString()
    println(gameboardb.gamefield)

    val loop = new Breaks
    loop.breakable {
      while(true) {
        println("Pleas input 4 colors like this: color1 color2 color3 color4")
        val input = readLine()
        if(input.equals("exit")) {
          loop.break
        }
        val colors = input.split(" ")
        println(GameBoard(GameData().initializeEmpty().updateAttempt(9,gameboardb.gamedata.attempts(9).updateColor(0,colors(0))),"").gameFieldString().gamefield)
      }
    }
    println("Goodbye!")
  }
}
