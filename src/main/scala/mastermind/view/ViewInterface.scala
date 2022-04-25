package mastermind.view

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.*
import akka.http.scaladsl.model.StatusCodes.*
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.Unmarshaller
import com.google.inject.Guice
import play.api.libs.json.Json
import spray.json.*
import spray.json.DefaultJsonProtocol.*


import scala.io.StdIn

object ViewInterface {

  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem(Behaviors.empty, "my-system")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.executionContext


    def viewStateRoute: Route = post {
      path("viewState") {
        entity(as[String]) { str =>
          // TODO Difficulty as Json -> Serialize new class for difficulty
          println(str.parseJson.prettyPrint)

          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))

        }
      }
    }



    val routes = viewStateRoute

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(routes)

    println(s"Server now online. Please navigate to http://localhost:8080/hello\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done

  }


}
