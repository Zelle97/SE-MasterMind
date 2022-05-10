package mastermind.persistence.fileIOComponent.dataBase.slickImpl

import slick.jdbc.PostgresProfile.api.*

class GameDataTable(tag: Tag) extends Table[(Int, Int)](tag, "GAMEDATA"){
  def id = column[Int]("GAMEDATA_ID", O.PrimaryKey)
  def turn = column[Int]("Turn")

  def * = (id, turn)
}
val gameDataTableQuery = TableQuery[GameDataTable]

class SolutionTable(tag: Tag) extends Table[(Int, Int, String, String, String, String)](tag, "SOLUTION") {
  def id = column[Int]("SOLUTION_ID", O.PrimaryKey)
  def gameDataId = column[Int]("GAMEDATA_ID")
  def color1 = column[String]("Color1")
  def color2 = column[String]("Color2")
  def color3 = column[String]("Color3")
  def color4 = column[String]("Color4")

  def * = (id, gameDataId, color1, color2, color3, color4)

  def gameDateFK = foreignKey("GAMEDATA_FK", gameDataId, gameDataTableQuery)(targetColumns = _.id, onDelete = ForeignKeyAction.Cascade)
}
val solutionTableQuery = TableQuery[SolutionTable]

class AttemptTable(tag: Tag) extends Table[(Int, String, String, String, String, Int)] (tag, "ATTEMPT") {
  def id = column[Int]("ATTEMPT_ID", O.PrimaryKey)
  def color1 = column[String]("Color1")
  def color2 = column[String]("Color2")
  def color3 = column[String]("Color3")
  def color4 = column[String]("Color4")

  def attemptsID = column[Int]("GAMEDATA_ATTEMPTS_ID")

  def * = (id, color1, color2, color3, color4, attemptsID)

  def gameDateAttemptsFK = foreignKey("GAMEDATA_ATTEMPTS_ID", attemptsID, gameDataAttemptsTableQuery)(targetColumns = _.id, onDelete = ForeignKeyAction.Cascade)

}

class GameDataAttempts(tag: Tag) extends Table[(Int, Int)] (tag, "ATTEMPTS") {
  def id = column[Int]("GAMEDATA_ATTEMPTS_ID", O.PrimaryKey)
  def gameDataId = column[Int]("GAMEDATA_ID")

  def * = (id, gameDataId)

  def gameDateFK = foreignKey("GAMEDATA_FK", gameDataId, gameDataTableQuery)(targetColumns = _.id, onDelete = ForeignKeyAction.Cascade)
}
val gameDataAttemptsTableQuery = TableQuery[GameDataAttempts]
