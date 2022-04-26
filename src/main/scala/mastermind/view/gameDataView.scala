package mastermind.view

import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.colorComponent.colorBaseImpl.Color
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol.*

case class GameStateView(gameData: GameDataView, gameString: String, state: String) {}
object GameStateView {
  implicit val gameStateJsonFormat: RootJsonFormat[GameStateView] =
    jsonFormat3(GameStateView.apply)
}

case class GameDataView(attempts: Vector[AttemptView], solution: Vector[ColorView], turn: Int) {}
object GameDataView {
  implicit val gameDataJsonFormat: RootJsonFormat[GameDataView] =
    jsonFormat3(GameDataView.apply)
}

case class AttemptView(userPickedColors: Vector[ColorView]) {}
object AttemptView {
  implicit val attemptJsonFormat: RootJsonFormat[AttemptView] =
    jsonFormat1(AttemptView.apply)
}

case class ColorView(colorString: String) {}
object ColorView {
  implicit val colorJsonFormat: RootJsonFormat[ColorView] =
    jsonFormat1(ColorView.apply)
}
