package mastermind.persistence.fileIOComponent.dataBase.slickImpl

import mastermind.core.DifficultyStrategy
import mastermind.core.model.attemptComponent.AttemptInterface
import mastermind.persistence.fileIOComponent.dataBase.slickImpl.GameDataTable
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.persistence.fileIOComponent.dataBase.DaoInterface
import org.scalactic.TypeCheckedTripleEquals.convertToCheckingEqualizer
import org.scalactic.TripleEquals.unconstrainedEquality
import slick.jdbc.GetResult
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.PostgresProfile.api.*

import java.util.UUID
import concurrent.ExecutionContext.Implicits.global
import scala.collection.mutable.ListBuffer
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

    val gameDataQuery = gameDataTableQuery.filter(_.id === gameDataId)
    val solutionQuery = solutionTableQuery.filter(_.gameDataId === gameDataId)
    val attempt_ids = gameDataAttemptsTableQuery.filter(_.gameDataId === gameDataId).map(_.attemptId)
    val attemptQuery = attemptTableQuery.filter(_.id in attempt_ids)

    //val gameDataQuery = sql"""select * from GAMEDATA where GAMEDATA_ID = $gameDataId""".as[(Int, Int)]
    //val solutionQuery = sql"""select * from SOLUTION where GAMEDATA_ID = $gameDataId""".as[(Int, Int, String, String, String, String)]
    //val attemptQuery = sql"""select * from ATTEMPT where ATTEMPT_ID = (select ATTEMPT_ID from GAMEDATA_ATTEMPT where gameDataId = $gameDataId)""".as[(Some)]

    val gameDataResult = Await.result(database.run(gameDataQuery.result), Duration.Inf).head
    val solutionResult = Await.result(database.run(solutionQuery.result), Duration.Inf).head
    val attemptResult = Await.result(database.run(attemptQuery.result), Duration.Inf).head

    val attemptSize = attemptResult.size
    var attempts = Vector[AttemptInterface]()
    attemptSize match {
      case 7 => attempts = DifficultyStrategy.getAttempts("mastermind")
      case 8 => attempts = DifficultyStrategy.getAttempts("medium")
      case 10 => attempts = DifficultyStrategy.getAttempts("easy")
    }

    null
  }

  override def save(gameData: GameData): Unit = {
    val solutionAsString = gameData.solution.map(color => color.colorString)
    val attempts = getAttemptsAsList(gameData.attempts)
    val gameDataAttempts = getGameDataAttemptsAsList(attempts)
    val setup = DBIO.seq(
      (gameDataTableQuery.schema ++ solutionTableQuery.schema ++ gameDataAttemptsTableQuery.schema ++ attemptTableQuery.schema)
        .createIfNotExists,
      //gameDataTableQuery.delete
      gameDataTableQuery += (gameDataId, gameData.turn),

      solutionTableQuery += (UUID.randomUUID().getMostSignificantBits, gameDataId, solutionAsString(0), solutionAsString(1), solutionAsString(2), solutionAsString(3)),

      attemptTableQuery ++= attempts,

      gameDataAttemptsTableQuery ++= gameDataAttempts
    )
    database.run(setup)
  }

  def getAttemptsAsList(attempts: Vector[AttemptInterface]): Seq[(Long, Int, String, String, String, String)] = {
    attempts.zipWithIndex.map(attempt => {
      (UUID.randomUUID().getMostSignificantBits, attempt._2, attempt._1.userPickedColors(0).colorString,
        attempt._1.userPickedColors(1).colorString, attempt._1.userPickedColors(2).colorString, attempt._1.userPickedColors(3).colorString)
    })
  }

  def getGameDataAttemptsAsList(attemptTableList: Seq[(Long, Int, String, String, String, String)]) : Seq[(Long, Int)] = {
    attemptTableList.map(attemptSeq => {
      (attemptSeq._1, gameDataId)
    })
  }
}
