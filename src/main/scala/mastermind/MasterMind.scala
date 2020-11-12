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
      var a = 9
      while(true && a != -1) {

        println("Pleas input 4 colors like this: color1 color2 color3 color4")
        val input = readLine()
        if(input.equals("exit")) {
          loop.break
        }
        val colors = input.split(" ").toVector
        val attempt = Attempt().updateAllColor(colors)


        //println(attempt.userPickedColors)
        println(GameBoard(GameData().initializeEmpty().updateAttempt(a,attempt),"").gameFieldString().gamefield)
        a = a -1
      }
    }
    println("Goodbye!")
  }
}
