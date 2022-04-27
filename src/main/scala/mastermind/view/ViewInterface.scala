package mastermind.view

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding.Post
import akka.http.scaladsl.model.*
import akka.http.scaladsl.model.StatusCodes.*
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.Unmarshaller
import com.google.inject.Guice
import play.api.libs.json.Json
import spray.json.*
import spray.json.DefaultJsonProtocol.*
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*

import akka.actor.TypedActor.dispatcher
import concurrent.ExecutionContext.Implicits.global

import scala.io.StdIn
import scala.io.StdIn.readLine

object ViewInterface {

  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem(Behaviors.empty, "view")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.executionContext

    val tui = new TUI

    def viewStateRoute: Route = post {
      path("viewState") {
        (entity(as[GameStateView])) { gameStateView =>
          tui.reactToGameState(gameStateView)
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))

        }
      }
    }



    val routes = viewStateRoute

    val bindingFuture = Http().newServerAt("localhost", 8081).bind(routes)

    println(s"Server now online.Please navigate to http://localhost:8081/\nType 'exit' to stop...")
    //StdIn.readLine() // let it run until user presses return
    tui.welcome()
    var input: String = ""
    while
      input = readLine()
      tui.processInput(input)
      input != "exit"
    do ()
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done

  }

  def postDifficulty(diff: String): Unit = {
    Post("http://localhost:8080/difficulty", new DifficultyView(diff))
  }


}
