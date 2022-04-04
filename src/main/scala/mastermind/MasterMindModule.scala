package mastermind

import com.google.inject.AbstractModule
import mastermind.controllerComponent.controllerBaseImpl.Controller
import mastermind.controllerComponent.{ControllerInterface, DifficultyStrategy}
import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.model.colorComponent.ColorFactoryInterface
import mastermind.model.colorComponent.colorFactoryBaseImpl.ColorFactory
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

    bind(classOf[ColorFactoryInterface]).toInstance(colorFactory)
    bind(classOf[AttemptInterface]).toInstance(attempt)
    bind(classOf[GameDataInterface]).toInstance(gameData)
    bind(classOf[ControllerInterface]).toInstance(new Controller(gameData, colorFactory))
  }
}
