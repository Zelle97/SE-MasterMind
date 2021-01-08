package mastermind.model

import mastermind.model.gameDataComponent.GameDataInterface

case class GameBoard(gamedata: GameDataInterface, gamefield: String = "") {


  def gameToString(): GameBoard = {
    GameBoard(gamedata, concatTitle() +  concatLineBreak() +concatEachSlotField())
  }


  def concatEachSlotField(): String = {
    var v = ""
    for (i <- gamedata.getAllAttempts().indices) {
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
      v += gamedata.getAttempt(slotField).getUserPickedColor(i).getColor
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
    " Correct Positions: " + gamedata.getAttempt(slotField).getCorrectPositions(gamedata.getSolution())
  }

  def concatCorrectColors(slotField: Int): String = {
    " Correct Colors: " + gamedata.getAttempt(slotField).getCorrectColors(gamedata.getSolution())
  }

  def concatLineBreak(): String = {
    "\n"
  }
}
