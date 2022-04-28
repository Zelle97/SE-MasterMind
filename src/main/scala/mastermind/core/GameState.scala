package mastermind.core

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse}
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.core.util.{GameOver, InGame, Win}
import mastermind.view.InputView
import akka.http.scaladsl.client.RequestBuilding.Post
import spray.json.*
import spray.json.DefaultJsonProtocol.*
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse}

import scala.concurrent.Future
import scala.swing.event.Event

case class GameState(var gameData: GameData){
  def handle(e: Event): GameState = {
    e match {
      case d: InGame =>
        gameData = d.gameData
        post("InGame")
      case w: Win =>
        gameData = w.gameData
        post("Win")
      case l: GameOver =>
        gameData = l.gameData
        post("GameOver")
    }
    this
  }
  def post(state: String): Unit = {
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    implicit val executionContext = system.executionContext
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = "http://localhost:8081/viewState",
      entity = HttpEntity(ContentTypes.`application/json`, new GameStateCore(gameData, gameData.toString(), state).toJson.prettyPrint)
    ))
  }
}
