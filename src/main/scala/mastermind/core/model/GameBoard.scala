package mastermind.core.model

import mastermind.core.model.gameDataComponent.GameDataInterface

import scala.annotation.tailrec

case class GameBoard(gamedata: GameDataInterface, gamefield: String = "") {
  def gameToString(): GameBoard =
    GameBoard(gamedata, new StringBuilder(concatTitle()).append(concatLineBreak()).append(concatEachSlotField()).toString())
  def concatEachSlotField(): String =
    val builder = new StringBuilder
    gamedata.attempts.indices.foreach(i => {
      builder.append(concatHorizontalLine())
      builder.append(concatLineBreak())
      builder.append(concatSlotPadding())
      builder.append(concatCorrectColors(i))
      builder.append(concatLineBreak())
      builder.append(forEachSlot(i))
      builder.append(concatLineBreak())
      builder.append(concatSlotPadding())
      builder.append(concatCorrectPositions(i))
      builder.append(concatLineBreak())
      builder.append(concatHorizontalLine())
      builder.append(concatLineBreak())
    })
    builder.toString()
  def forEachSlot(slotField: Int): String =
    val builder = new StringBuilder
    loop(0)
    @tailrec
    def loop(i: Int): Unit =
      if i <= 3 then
        builder.append(concatVerticalLine())
        builder.append(gamedata.getAttempt(slotField).userPickedColors(i).getColor)
        builder.append(concatVerticalLine())
        loop(i + 1)
    builder.toString()
  def concatSlotPadding(): String =
    val builder = new StringBuilder
    loop(0)
    @tailrec
    def loop(i: Int): Unit =
      if i <= 3 then
        builder.append(concatVerticalLine())
        builder.append(concatEmptySpace())
        builder.append(concatVerticalLine())
        loop(i + 1)
    builder.toString()
  def concatTitle(): String = "\t\t\t\tMaster Mind"
  def concatVerticalLine(): String = "|"
  def concatHorizontalLine(): String = "------------------------------------------------"
  def concatEmptySpace(): String = "          "
  def concatCorrectPositions(slotField: Int): String =
    " Correct Positions: " + gamedata.getAttempt(slotField).getCorrectPositions(gamedata.solution)
  def concatCorrectColors(slotField: Int): String =
    " Correct Colors: " + gamedata.getAttempt(slotField).getCorrectColors(gamedata.solution)
  def concatLineBreak(): String = "\n"
}
