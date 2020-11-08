package mastermind

object MasterMind {

  def main(args: Array[String]): Unit = {
    println("Welcome to MasterMind!")
    val gb = Gameboard(gameField(), "").gameFieldString()

    println(gb.gamefield)
  }

  def gameField(): Vector[Vector[String]] = {
    var gamefield = Vector[Vector[String]]()
    val emptyFields = Vector[String]("          ", "          ", "          ", "          ")
    for (i <- 1 to 9) {
      gamefield = gamefield :+ emptyFields
    }
    gamefield :+ Vector[String]("          ", "          ", "          ", "          ")
  }

}
