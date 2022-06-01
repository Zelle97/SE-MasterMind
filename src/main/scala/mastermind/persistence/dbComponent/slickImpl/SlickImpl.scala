package mastermind.persistence.dbComponent.slickImpl

import com.google.inject.Inject
import mastermind.persistence.dbComponent.slickImpl.DatabaseSchemas
import mastermind.core.DifficultyStrategy
import mastermind.core.model.attemptComponent.AttemptInterface
import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.persistence.dbComponent.DaoInterface
import org.scalactic.TypeCheckedTripleEquals.convertToCheckingEqualizer
import org.scalactic.TripleEquals.unconstrainedEquality
import slick.jdbc.GetResult
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.MySQLProfile.api.*
import mastermind.core.model.colorComponent.colorBaseImpl.Color

import scala.util.{Failure, Success}
import java.util.UUID
import concurrent.ExecutionContext.Implicits.global
import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}
import scala.language.postfixOps
import scala.util.Try

class SlickImpl @Inject() extends DaoInterface {

  val database = Database.forConfig("mariadb")
  val gameDataId = 1

  override def load: GameData = {

    val gameDataQuery = sql"""select * from GAMEDATA where GAMEDATA_ID = $gameDataId""".as[(Int, Int)]
    val solutionQuery = sql"""select * from SOLUTION where GAMEDATA_ID = $gameDataId """.as[(Long, Int, String, String, String, String)]
    val attemtQuery = sql"""select * from ATTEMPT where GAMEDATA_ID = $gameDataId """.as[(Long, Int, Int, String, String, String, String)]

    val gameDataResult = Await.result(database.run(gameDataQuery), Duration.Inf).head
    val solutionResult = Await.result(database.run(solutionQuery), Duration.Inf).head
    val attemptResult = Await.result(database.run(attemtQuery), Duration.Inf)

    val turn = gameDataResult(1)
    val solutionColor1 = solutionResult(2)
    val solutionColor2 = solutionResult(3)
    val solutionColor3 = solutionResult(4)
    val solutionColor4 = solutionResult(5)

    val attemptOrder = attemptResult(2)
    val attemptColor1 = attemptResult(3)
    val attemptColor2 = attemptResult(4)
    val attemptColor3 = attemptResult(5)
    val attemptColor4 = attemptResult(6)

    val sorted = attemptResult.sortWith(_._3 < _._3)
    val attempts = sorted.map(attempt => Attempt(Vector(Color(attempt._4),Color(attempt._5),Color(attempt._6),Color(attempt._7))))

    GameData(attempts, Vector(Color(solutionColor1),Color(solutionColor2),Color(solutionColor3),Color(solutionColor4)), turn)
  }

  override def save(gameData: GameData): Unit = {
    val solutionAsString = gameData.solution.map(color => color.colorString)
    val attempts = getAttemptsAsList(gameData.attempts)
    val setup = DBIO.seq(
      (DatabaseSchemas.gameDataTableQuery.schema ++ DatabaseSchemas.solutionTableQuery.schema ++ DatabaseSchemas.attemptTableQuery.schema)
        .createIfNotExists,
      DatabaseSchemas.gameDataTableQuery.delete,
      DatabaseSchemas.solutionTableQuery.delete,
      DatabaseSchemas.attemptTableQuery.delete,
      DatabaseSchemas.gameDataTableQuery += (gameDataId, gameData.turn),
      DatabaseSchemas.solutionTableQuery += (UUID.randomUUID().getMostSignificantBits, gameDataId, solutionAsString(0), solutionAsString(1), solutionAsString(2), solutionAsString(3)),
      DatabaseSchemas.attemptTableQuery ++= attempts,
    )
    val setupFuture = database.run(setup).andThen {
      case Success(_) => println("Daten erfolgreich gespeichert")
      case Failure(e) => println(s"Fehler beim Speichern in die Datenbank: ${e.getMessage}")
    }
  }

  def getAttemptsAsList(attempts: Vector[AttemptInterface]): Seq[(Long, Int, Int, String, String, String, String)] = {
    attempts.zipWithIndex.map((attempt, index) => {
      (UUID.randomUUID().getMostSignificantBits, gameDataId, index, attempt.userPickedColors(0).colorString,
      attempt.userPickedColors(1).colorString, attempt.userPickedColors(2).colorString, attempt.userPickedColors(3).colorString)
    })
  }
}
