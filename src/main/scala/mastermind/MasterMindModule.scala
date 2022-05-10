package mastermind

import com.google.inject.AbstractModule
import mastermind.core.{ControllerInterface, DifficultyStrategy, GameState}
import mastermind.core.controllerBaseImpl.Controller
import mastermind.core.GameState
import mastermind.core.model.attemptComponent.AttemptInterface
import mastermind.core.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.core.model.colorComponent.ColorFactoryInterface
import mastermind.core.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.persistence.fileIOComponent.FileIOInterface
import mastermind.persistence.fileIOComponent.fileIOJsonImpl.FileIO
import mastermind.core.model.gameDataComponent.GameDataInterface
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
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
