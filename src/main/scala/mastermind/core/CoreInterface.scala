package mastermind.core

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.model.StatusCodes.*
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.*
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.Unmarshaller
import akka.http.scaladsl.model.HttpEntity
import com.google.inject.Guice
import mastermind.MasterMindModule
import play.api.libs.json.Json
import spray.json.*
import spray.json.DefaultJsonProtocol.*
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object CoreInterface {

  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "core")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContextExecutor = system.executionContext


    val injector = Guice.createInjector(new MasterMindModule)
    val controller = injector.getInstance(classOf[ControllerInterface])

    def gameRoutes:Route = {
      path("game") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, controller.gameState.gameData.toString()))
        }
      }
    }

    def attemptRoutes:Route = {
      path("attempt") {
        post {
          controller.addAttempt("red red red red")
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))
        }
      }
    }

    def difficultyRoutes:Route = {
      path("difficulty") {
        post {
          entity(as[Difficulty]) { difficulty =>
            // TODO Difficulty as Json -> Serialize new class for difficulty
            println(difficulty.diff)
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))
          }
        }
      }
    }

    val routes = gameRoutes ~ attemptRoutes ~ difficultyRoutes

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(routes)

    println(s"Server now online. Please navigate to http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
