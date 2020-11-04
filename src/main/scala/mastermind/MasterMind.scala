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
    var gameboard = ""
    gameboard = concatTitle(gameboard)
    gameboard = concatLineBreak(gameboard)
    gamefield.foreach(gamefieldSlot => {
      gameboard = concatHorizontalLine(gameboard)
      gameboard = concatLineBreak(gameboard)
      gameboard = concatSlotPadding(gameboard)
      gameboard = concatCorrectColors(gameboard)
      gameboard = concatLineBreak(gameboard)
      gamefieldSlot.foreach(slot => {
        gameboard = concatVerticalLine(gameboard)
        gameboard = gameboard.concat(slot)
        gameboard = concatVerticalLine(gameboard)
      })
      gameboard = concatLineBreak(gameboard)
      gameboard = concatSlotPadding(gameboard)
      gameboard = concatCorrectPositions(gameboard)
      gameboard = concatLineBreak(gameboard)
      gameboard = concatHorizontalLine(gameboard)
      gameboard = concatLineBreak(gameboard)
    })
    gameboard
  }

  def concatSlotPadding(gameboard: String): String = {
    var gameboardCopy = gameboard
    for (i <- 1 to 4) {
      gameboardCopy = concatVerticalLine(gameboardCopy)
      gameboardCopy = concatEmptySpace(gameboardCopy)
      gameboardCopy = concatVerticalLine(gameboardCopy)
    }
    gameboardCopy
  }

  def concatTitle(gameboard: String): String = {
    val title = "\t\t\t\tMaster Mind"
    gameboard.concat(title)
  }

  def concatVerticalLine(gameboard: String): String = {
    gameboard.concat("|")
  }

  def concatHorizontalLine(gameboard: String): String = {
    gameboard.concat("------------------------------------------------")
  }

  def concatLineBreak(gameboard: String): String = {
    gameboard.concat("\n")
  }

  def concatEmptySpace(gameboard: String): String = {
    gameboard.concat("          ")
  }

  def concatCorrectPositions(gameboard: String): String = {
    gameboard.concat(" Correct Positions: 0")
  }

  def concatCorrectColors(gameboard: String): String = {
    gameboard.concat(" Correct Colors: 0")
  }

}
