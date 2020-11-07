package mastermind

case class Gameboard(gamedata: Vector[Vector[String]], gamefield: String) {






  def gameFieldString(): Gameboard = {
    Gameboard(gamedata, gamefield).
      concatTitle().
      concatLineBreak()
      .concatGamefield()
  }
 def forEachSlotfield(sf: Int, s: Int): Gameboard = {
   Gameboard(gamedata, gamefield).concatHorizontalLine().concatLineBreak().concatSlotPadding().concatCorrectColors().concatLineBreak().forEachSlot(sf,s)
     .concatLineBreak()
     .concatSlotPadding()
     .concatCorrectPositions()
     .concatLineBreak()
     .concatHorizontalLine()
     .concatLineBreak()
 }
 def concatGamefield(): Gameboard = {
    Gameboard(gamedata, gamefield).forEachSlotfield(0,0)
   .forEachSlotfield(1,0)
   .forEachSlotfield(2,0)
   .forEachSlotfield(3,0)
   .forEachSlotfield(4,0)
   .forEachSlotfield(5,0)
   .forEachSlotfield(6,0)
   .forEachSlotfield(7,0)
   .forEachSlotfield(8,0)
   .forEachSlotfield(9,0)
  }

  def forEachSlot(sf: Int, s: Int): Gameboard = {
    Gameboard(gamedata, gamefield).concatVerticalLine().addString(gamedata(sf)(s)).concatVerticalLine()
      .concatVerticalLine().addString(gamedata(sf)(s+1)).concatVerticalLine()
      .concatVerticalLine().addString(gamedata(sf)(s+2)).concatVerticalLine()
      .concatVerticalLine().addString(gamedata(sf)(s+3)).concatVerticalLine()

  }

  def concatSlotPadding(): Gameboard = {

      Gameboard(gamedata, gamefield).concatVerticalLine().concatEmptySpace().concatVerticalLine()
        .concatVerticalLine().concatEmptySpace().concatVerticalLine()
        .concatVerticalLine().concatEmptySpace().concatVerticalLine()
        .concatVerticalLine().concatEmptySpace().concatVerticalLine()

  }

  def addString(string: String): Gameboard = {
    Gameboard(gamedata, gamefield.concat(string))
  }

  def concatTitle(): Gameboard = {
    Gameboard(gamedata, gamefield.concat("\t\t\t\tMaster Mind"))
  }


  def concatVerticalLine(): Gameboard = {
    Gameboard(gamedata, gamefield.concat("|"))
  }

  def concatHorizontalLine(): Gameboard = {
    Gameboard(gamedata, gamefield.concat("------------------------------------------------"))
  }

  def concatLineBreak(): Gameboard = {
    Gameboard(gamedata, gamefield.concat("\n"))
  }

  def concatEmptySpace(): Gameboard = {
    Gameboard(gamedata, gamefield.concat("          "))
  }

  def concatCorrectPositions(): Gameboard = {
    Gameboard(gamedata, gamefield.concat(" Correct Positions: 0"))
  }

  def concatCorrectColors(): Gameboard = {
    Gameboard(gamedata, gamefield.concat(" Correct Colors: 0"))
  }
}
