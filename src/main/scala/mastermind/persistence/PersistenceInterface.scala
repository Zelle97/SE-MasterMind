package mastermind.persistence

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{as, complete, concat, entity, get, path, post}
import akka.http.scaladsl.server.Route
import com.google.inject.Guice
import mastermind.MasterMindModule
import mastermind.core.model.gameDataComponent.GameDataInterface
import akka.http.scaladsl.server.Directives.*
import spray.json.*
import spray.json.DefaultJsonProtocol.*
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.core.{ControllerInterface, Difficulty, Input}
import mastermind.persistence.dbComponent.DaoInterface
import mastermind.persistence.fileIOComponent.FileIOInterface

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object PersistenceInterface {
  val persistenceInterface = sys.env.getOrElse("PERSISTENCE_INTERFACE", "localhost")
  val persistencePort: Int = sys.env.getOrElse("PERSISTENCE_PORT", 8082).toString.toInt

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "persistence")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext
    val injector = Guice.createInjector(new MasterMindModule)
    val fileIO = injector.getInstance(classOf[FileIOInterface])
    val dbIO = injector.getInstance(classOf[DaoInterface])
    def routes:Route = {
      concat(
        path("game"/"save") {
          post {
            entity(as[GameData]) { gameData =>
              dbIO.save(gameData)
              complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))
            }
          }
        },
        path("game"/"load") {
          get {
            complete(HttpEntity(ContentTypes.`application/json`, dbIO.load.toJson.prettyPrint))
          }
        }
      )
    }
    val bindingFuture = Http().newServerAt(persistenceInterface,persistencePort).bind(routes)
    println(s"Server now online. Please navigate to http://$persistenceInterface:$persistencePort/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
