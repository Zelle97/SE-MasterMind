package mastermind.model

case class GameBoard(gamedata: GameData, gamefield: String) {

  def gameFieldString(): GameBoard = {
    GameBoard(gamedata, gamefield)
      .concatTitle()
      .concatLineBreak()
      .concatGamefield()
  }

  def concatLineBreak(): GameBoard = {
    GameBoard(gamedata, gamefield.concat("\n"))
  }

  def concatGamefield(): GameBoard = {
    GameBoard(gamedata, gamefield)
      .forEachSlotfield(0)
      .forEachSlotfield(1)
      .forEachSlotfield(2)
      .forEachSlotfield(3)
      .forEachSlotfield(4)
      .forEachSlotfield(5)
      .forEachSlotfield(6)
      .forEachSlotfield(7)
      .forEachSlotfield(8)
      .forEachSlotfield(9)
  }

  def forEachSlotfield(slotField: Int): GameBoard = {
    GameBoard(gamedata, gamefield)
      .concatHorizontalLine()
      .concatLineBreak()
      .concatSlotPadding()
      .concatCorrectColors(slotField)
      .concatLineBreak()
      .forEachSlot(slotField)
      .concatLineBreak()
      .concatSlotPadding()
      .concatCorrectPositions(slotField)
      .concatLineBreak()
      .concatHorizontalLine()
      .concatLineBreak()
  }

  def forEachSlot(slotField: Int): GameBoard = {
    GameBoard(gamedata, gamefield)
      .concatVerticalLine().addString(gamedata.attempts(slotField).userPickedColors(0).getColor()).concatVerticalLine()
      .concatVerticalLine().addString(gamedata.attempts(slotField).userPickedColors(1).getColor()).concatVerticalLine()
      .concatVerticalLine().addString(gamedata.attempts(slotField).userPickedColors(2).getColor()).concatVerticalLine()
      .concatVerticalLine().addString(gamedata.attempts(slotField).userPickedColors(3).getColor()).concatVerticalLine()
  }

  def concatSlotPadding(): GameBoard = {
    GameBoard(gamedata, gamefield).concatVerticalLine().concatEmptySpace().concatVerticalLine()
      .concatVerticalLine().concatEmptySpace().concatVerticalLine()
      .concatVerticalLine().concatEmptySpace().concatVerticalLine()
      .concatVerticalLine().concatEmptySpace().concatVerticalLine()
  }

  def addString(stringToConcat: String): GameBoard = {
    GameBoard(gamedata, gamefield.concat(stringToConcat))
  }

  def concatTitle(): GameBoard = {
    GameBoard(gamedata, gamefield.concat("\t\t\t\tMaster Mind"))
  }

  def concatVerticalLine(): GameBoard = {
    GameBoard(gamedata, gamefield.concat("|"))
  }

  def concatHorizontalLine(): GameBoard = {
    GameBoard(gamedata, gamefield.concat("------------------------------------------------"))
  }

  def concatEmptySpace(): GameBoard = {
    GameBoard(gamedata, gamefield.concat("          "))
  }

  def concatCorrectPositions(slotField: Int): GameBoard = {
    GameBoard(gamedata, gamefield.concat(" Correct Positions: " + gamedata.attempts(slotField).getCorrectPositions(gamedata.solution)))
  }

  def concatCorrectColors(slotField: Int): GameBoard = {
    GameBoard(gamedata, gamefield.concat(" Correct Colors: " + gamedata.attempts(slotField).getCorrectColors(gamedata.solution)))
  }
}
