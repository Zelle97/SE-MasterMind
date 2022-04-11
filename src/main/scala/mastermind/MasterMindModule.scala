package mastermind

import com.google.inject.AbstractModule
import mastermind.controllerComponent.controllerBaseImpl.Controller
import mastermind.controllerComponent.{ControllerInterface, DifficultyStrategy, GameState}
import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.model.colorComponent.ColorFactoryInterface
import mastermind.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.model.fileIOComponent.FileIOInterface
import mastermind.model.fileIOComponent.fileIOXmlImpl.FileIO
import mastermind.model.gameDataComponent.GameDataInterface
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import net.codingwell.scalaguice.ScalaModule

class MasterMindModule extends AbstractModule {
  override def configure(): Unit = {
    val colorFactory = ColorFactory()
    val attempt = Attempt()
    val solution = colorFactory.pickSolution()
    val attempts = DifficultyStrategy.getAttempts()
    val gameData = GameData(attempts, solution)
    val gameState = GameState(gameData)

    
    bind(classOf[ColorFactoryInterface]).toInstance(colorFactory)
    bind(classOf[AttemptInterface]).toInstance(attempt)
    bind(classOf[GameDataInterface]).toInstance(gameData)
    bind(classOf[ControllerInterface]).toInstance(new Controller(gameState, colorFactory))
    bind(classOf[FileIOInterface]).toInstance(new FileIO)
  }
}
