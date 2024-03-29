package mastermind.persistence.dbComponent.slickImpl

import slick.jdbc.MySQLProfile.api.*

object DatabaseSchemas {
  class GameDataTable(tag: Tag) extends Table[(Int, Int)](tag, "GAMEDATA"){
    def id = column[Int]("GAMEDATA_ID", O.PrimaryKey)
    def turn = column[Int]("Turn")

    def * = (id, turn)
  }
  val gameDataTableQuery = TableQuery[GameDataTable]

  class SolutionTable(tag: Tag) extends Table[(Long, Int, String, String, String, String)](tag, "SOLUTION") {
    def id = column[Long]("SOLUTION_ID", O.PrimaryKey)
    def gameDataId = column[Int]("GAMEDATA_ID")
    def color1 = column[String]("Color1")
    def color2 = column[String]("Color2")
    def color3 = column[String]("Color3")
    def color4 = column[String]("Color4")
    def * = (id, gameDataId, color1, color2, color3, color4)

    def gameDateFK = foreignKey("GAMEDATA_FK", gameDataId, gameDataTableQuery)(targetColumns = _.id, onDelete = ForeignKeyAction.Cascade)
  }
  val solutionTableQuery = TableQuery[SolutionTable]

  class AttemptTable(tag: Tag) extends Table[(Long, Int, Int, String, String, String, String)] (tag, "ATTEMPT") {
    def id = column[Long]("ATTEMPT_ID", O.PrimaryKey)
    def gameDataId = column[Int]("GAMEDATA_ID")
    def order = column[Int]("Order")
    def color1 = column[String]("Color1")
    def color2 = column[String]("Color2")
    def color3 = column[String]("Color3")
    def color4 = column[String]("Color4")
    def * = (id, gameDataId, order, color1, color2, color3, color4)

  }
  val attemptTableQuery = TableQuery[AttemptTable]
}



