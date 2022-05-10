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
import mastermind.view.ViewInterface.{coreInterface, corePort}

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn



object CoreInterface {

  val viewInterface = sys.env.getOrElse("VIEW_INTERFACE", "localhost")
  val viewPort: Int = sys.env.getOrElse("VIEW_PORT", 8081).toString.toInt
  val coreInterface = sys.env.getOrElse("CORE_INTERFACE", "localhost")
  val corePort: Int = sys.env.getOrElse("CORE_PORT", 8080).toString.toInt

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "core")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext
    val injector = Guice.createInjector(new MasterMindModule)
    val controller = injector.getInstance(classOf[ControllerInterface])
    def gameRoutes:Route = {
      concat(
        path("game") {
          get {
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, controller.gameState.gameData.toString()))
          }
        },
        path("game"/"undo") {
          post {
            controller.undo()
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))
          }
        },
        path("game"/"redo") {
          post {
            controller.redo()
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))
          }
        },
          path("game"/"save") {
          post {
            controller.save()
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))
          }
        },
        path("game"/"load") {
          post {
            controller.load()
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))
          }
        }
      )
    }
    def attemptRoutes:Route = {
      path("attempt") {
        post {
          entity(as[Input]) { obj =>
            controller.addAttempt(obj.input)
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))
          }
        }
      }
    }
    def difficultyRoutes:Route = {
      path("difficulty") {
        post {
          entity(as[Difficulty]) { obj =>
            controller.setDifficulty(obj.diff)
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))
          }
        }
      }
    }
    val routes = gameRoutes ~ attemptRoutes ~ difficultyRoutes
    val bindingFuture = Http().newServerAt(coreInterface,corePort).bind(routes)
    println(s"Settings for view: http://$viewInterface:$viewPort/")
    println(s"Server now online. Please navigate to http://$coreInterface:$corePort/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
