package mastermind.persistence.dbComponent.slickImpl

import mastermind.persistence.dbComponent.slickImpl.GameDataTable
import mastermind.core.DifficultyStrategy
import mastermind.core.model.attemptComponent.AttemptInterface
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.persistence.dbComponent.DaoInterface
import org.scalactic.TypeCheckedTripleEquals.convertToCheckingEqualizer
import org.scalactic.TripleEquals.unconstrainedEquality
import slick.jdbc.GetResult
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.MySQLProfile.api.*

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
    val attemptQuery = attemptTableQuery.filter(_.gameDataId === gameDataId)

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
    val setup = DBIO.seq(
      (gameDataTableQuery.schema ++ solutionTableQuery.schema ++ attemptTableQuery.schema)
        .createIfNotExists,
      gameDataTableQuery.delete,
      gameDataTableQuery += (gameDataId, gameData.turn),

      solutionTableQuery += (UUID.randomUUID().getMostSignificantBits, gameDataId, solutionAsString(0), solutionAsString(1), solutionAsString(2), solutionAsString(3)),

      attemptTableQuery ++= attempts,
    )
    database.run(setup)
  }

  def getAttemptsAsList(attempts: Vector[AttemptInterface]): Seq[(Long, Int, Int, String, String, String, String)] = {
    attempts.zipWithIndex.map(attempt => {
      (UUID.randomUUID().getMostSignificantBits, gameDataId, attempt._2, attempt._1.userPickedColors(0).colorString,
        attempt._1.userPickedColors(1).colorString, attempt._1.userPickedColors(2).colorString, attempt._1.userPickedColors(3).colorString)
    })
  }
}
