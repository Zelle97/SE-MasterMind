package mastermind.model

case class GameBoard(gamedata: GameData, gamefield: Vector[String] = Vector[String]("")) {



  def gameToString(): GameBoard = {
    GameBoard(gamedata, gamefield :+ concatEachSlotField())
  }


  def concatEachSlotField(): String = {
    var v = ""
    for (i <- gamedata.attempts.indices) {
      v += concatHorizontalLine()
      v += concatLineBreak()
      v += concatSlotPadding()
      v += concatCorrectColors(i)
      v += concatLineBreak()
      v += forEachSlot(i)
      v += concatLineBreak()
      v += concatSlotPadding()
      v += concatCorrectPositions(i)
      v += concatLineBreak()
      v += concatHorizontalLine()
      v += concatLineBreak()
    }
    v
  }

  def forEachSlot(slotField: Int): String = {
    var v = ""
    for (i <- 0 to 3) {
      v += concatVerticalLine()
      v += gamedata.attempts(slotField).userPickedColors(i).getColor()
      v += concatVerticalLine()
    }
    v
  }

  def concatSlotPadding(): String = {
    var v = ""
    for (i <- 0 to 3) {
      v += concatVerticalLine()
      v += concatEmptySpace()
      v += concatVerticalLine()
    }
    v
  }


  def concatTitle(): String = {
    "\t\t\t\tMaster Mind"
  }

  def concatVerticalLine(): String = {
    "|"
  }

  def concatHorizontalLine(): String = {
    "------------------------------------------------"
  }

  def concatEmptySpace(): String = {
    "          "
  }

  def concatCorrectPositions(slotField: Int): String = {
    " Correct Positions: " + gamedata.attempts(slotField).getCorrectPositions(gamedata.solution)
  }

  def concatCorrectColors(slotField: Int): String = {
    " Correct Colors: " + gamedata.attempts(slotField).getCorrectColors(gamedata.solution)
  }

  def concatLineBreak(): String = {
    "\n"
  }
}
