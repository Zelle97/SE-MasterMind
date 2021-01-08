package mastermind

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import mastermind.controllerComponent.ControllerInterface
import mastermind.model.attemptComponent.AttemptInterface
import mastermind.model.colorComponent.ColorInterface
import mastermind.model.gameDataComponent.GameDataInterface
import net.codingwell.scalaguice.ScalaModule

class MasterMindModule extends AbstractModule with ScalaModule  {

  override def configure() = {
    bind[ColorInterface].to[model.colorComponent.colorBaseImpl.Color]
    bind[AttemptInterface].to[model.attemptComponent.attemptBaseImpl.Attempt]
    bind[GameDataInterface].to[model.gameDataComponent.gameDataBaseImpl.GameData]
    bind[ControllerInterface].to[controllerComponent.controllerBaseImpl.Controller]
  }
}
