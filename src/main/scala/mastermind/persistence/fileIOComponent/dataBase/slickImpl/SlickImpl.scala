package mastermind.persistence.fileIOComponent.dataBase.slickImpl

import mastermind.persistence.fileIOComponent.dataBase.slickImpl.GameDataTable.*
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.persistence.fileIOComponent.dataBase.DaoInterface
import slick.jdbc.GetResult
import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery

import java.lang.StackWalker
import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}
import scala.language.postfixOps
import scala.util.Try

class SlickImpl extends DaoInterface {

  val database = Database.forConfig("TODO")

  val gameDataId = 1

  override def load(): Try[GameData] = Try {
    loadGameData()
  }

  def loadGameData(): GameData = {
    val gameDataQuery2 = TableQuery[GameDataTable].filter(_.id === "1")
/*    val gameDateQuery = sql"""select * from GAMEDATA where GAMEDATA_ID = $gameDataId""".as[(Int, Int)]
    val solutionQuery = sql"""select * from SOLUTION where GAMEDATA_ID = $gameDataId""".as[(Int, Int, String, String, String, String)]
    val attemptsQuery = sql"""select * from ATTEMPTS where GAMEDATA_ID = $gameDataId""".as[(Int, Int)]
    val attemptQuery = sql"""select * from ATTEMPT where GAMEDATA_ATTEMPTS_ID = $gameDataId""".as[(Int, String, String, String, String, Int)]

    val gameDataResult = Await.result(database.run(gameDateQuery), Duration.Inf)*/




  }

}
