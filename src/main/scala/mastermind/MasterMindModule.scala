package mastermind

import com.google.inject.AbstractModule
import mastermind.controllerComponent.controllerBaseImpl.Controller
import mastermind.controllerComponent.{ControllerInterface, DifficultyStrategy}
import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.model.colorComponent.ColorInterface
import mastermind.model.colorComponent.colorBaseImpl.Color
import mastermind.model.fileIOComponent.FileIOInterface
import mastermind.model.gameDataComponent.GameDataInterface
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import net.codingwell.scalaguice.ScalaModule

class MasterMindModule extends AbstractModule with ScalaModule {

  override def configure() = {
    val color = Color()
    val attempt = Attempt()
    val solution = color.pickSolution()
    val attempts = DifficultyStrategy.getAttempts()
    val gameData = GameData(attempts, solution)
    val controller = new Controller(gameData, color)

    bind[ColorInterface].toInstance(color)
    bind[AttemptInterface].toInstance(attempt)
    bind[GameDataInterface].toInstance(gameData)
    bind[ControllerInterface].toInstance(controller)
    bind[FileIOInterface].toInstance(new model.fileIOComponent.fileIOXmlImpl.FileIO)

  }
}
