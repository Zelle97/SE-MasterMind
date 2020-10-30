package mastermind

object MasterMind {

  def main(args: Array[String]): Unit = {
    println("Welcome to MasterMind!")
    val gamefield = gameFieldString(gameField())
    println(gamefield)
  }

  def gameField(): Vector[Vector[String]] = {
    var gamefield = Vector[Vector[String]]()
    val emptyFields = Vector[String]("          ", "          ", "          ", "          ")
    for (i <- 1 to 10) {
      gamefield = gamefield :+ emptyFields
    }
    gamefield
  }

  def gameFieldString(gamefield: Vector[Vector[String]]): String = {
    val title = "\t\t\t\tMaster Mind\n"
    var gameboard = ""
    gameboard = gameboard.concat(title)

    gamefield.foreach(gamefieldSlot => {
      gameboard = gameboard.concat("------------------------------------------------\n")
      for (i <- 1 to 4) {
        gameboard = gameboard.concat("|")
        gameboard = gameboard.concat("          ")
        gameboard = gameboard.concat("|")
      }
      gameboard = gameboard.concat(" Correct Colors: 0")
      gameboard = gameboard.concat("\n")
      gamefieldSlot.foreach(slot => {
        gameboard = gameboard.concat("|")
        gameboard = gameboard.concat(slot)
        gameboard = gameboard.concat("|")
      })
      gameboard = gameboard.concat("\n")
      for (i <- 1 to 4) {
        gameboard = gameboard.concat("|")
        gameboard = gameboard.concat("          ")
        gameboard = gameboard.concat("|")
      }
      gameboard = gameboard.concat(" Correct Positions: 0\n")
      gameboard = gameboard.concat("------------------------------------------------\n")
    })

    gameboard
  }

}
