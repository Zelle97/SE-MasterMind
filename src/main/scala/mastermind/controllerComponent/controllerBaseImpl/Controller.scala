package mastermind.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import mastermind.MasterMindModule
import mastermind.controllerComponent.{ControllerInterface, DifficultyStrategy}
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.model.colorComponent.ColorInterface
import mastermind.model.fileIOComponent.FileIOInterface
import mastermind.model.gameDataComponent.GameDataInterface
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.util.{GameOver, InGame, UndoManager, Win}

import scala.swing.Publisher
import scala.util.{Failure, Success, Try}

class Controller @Inject()(var gameData: GameDataInterface,
                           var color: ColorInterface) extends ControllerInterface with Publisher {

  private val undoManager = new UndoManager


  def difficultyMatcher(difficulty: String): Option[String] = difficulty match {
    case "easy" => Some("easy")
    case "medium" => Some("medium")
    case "mastermind" => Some("mastermind")
    case _ => None
  }

  def setDifficulty(difficultyInput: String): Unit = {
    val difficulty = difficultyMatcher(difficultyInput)

    Try(GameData(DifficultyStrategy.getAttempts(difficulty.get), color.pickSolution())) match {
      case Success(newGameDate) =>
        gameData = newGameDate
        gameData.setTurn(0)
        publish(new InGame)
      case Failure(exception) =>
        print("Invalid Difficulty\n")
        print("Please use easy, medium or mastermind")
        print("\n")
    }
  }

  override def save(): Unit = {
    val injector = Guice.createInjector(new MasterMindModule)
    val io = injector.getInstance(classOf[FileIOInterface])
    io.save(gameData)
  }

  override def load(): Unit = {
    val injector = Guice.createInjector(new MasterMindModule)
    val io = injector.getInstance(classOf[FileIOInterface])
    gameData = io.load
    undoManager.clearList()
    publish(new InGame)
  }

  override def addAttempt(input: String): Unit = {
    val colors = input.split(" ").toVector
    Try(Attempt(colors.map(colorInput => color.apply(colorInput).get))) match {
      case Success(filledSuccess) =>
        if (filledSuccess.userPickedColors.size < 4) {
          print("Invalid Input\n")
        } else {
          undoManager.doStep(new AddCommand(gameData, filledSuccess, this))
          if (gameData.getAttempt(gameData.getAttemptSize() - gameData.getTurn()).getCorrectPositions(gameData.getSolution()) == 4) {
            publish(new Win)
            //System.exit(1)
          } else if (gameData.getTurn() == gameData.getAttemptSize()) {
            publish(new GameOver)
            //System.exit(1)
          } else {
            publish(new InGame)
          }
        }
      case Failure(exception) =>
        print("Invalid Input\n")
        print("Please use those colors: ")
        color.getAllColors.foreach(shade => print(shade + " "))
        print("\n")
    }
  }

  override def undo(): Unit = {
    undoManager.undoStep()
    publish(new InGame)
  }

  override def redo(): Unit = {
    undoManager.redoStep()
    publish(new InGame)
  }

  override def help(): Unit = {
    print("Welcome to Mastermind!\n")
    print("These are the available commands:\n")
    print("color1 color2 color3 color4 -> Input colors\n")
    print("s -> Save your Game\n")
    print("l -> Load your saved Game\n")
    print("z -> Undo your last Step\n")
    print("y -> Redo your last Step\n")
    print("h -> Display this help\n")
    print("exit -> Exit the game\n")
    print("And here is described how mastermind works:\n")
    print("You are the codebreaker: Try to guess the pattern in order and color.\nThere are three different difficulties:\neasy -> 10 turns\nmedium -> 8 turns\nmastermind -> 7 turns\nEach guess is made by placing a row of code pegs on the decoding board.\nOnce placed, you are provided with some feedback on the right side of the row with your guess.\nGood Luck!!\n")
  }

  override def getGameData(): GameDataInterface = {
    gameData
  }

  def gameToString: String = {
    gameData.toString()
  }

}
