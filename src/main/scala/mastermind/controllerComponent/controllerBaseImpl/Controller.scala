package mastermind.controllerComponent.controllerBaseImpl

import com.google.inject.Inject
import mastermind.controllerComponent.{ControllerInterface, DifficultyStrategy, GameState}
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.model.colorComponent.ColorFactoryInterface
import mastermind.model.colorComponent.colorFactoryBaseImpl.ColorFactory
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.util.{GameOver, InGame, UndoManager, Win}

import scala.swing.Publisher
import scala.util.{Failure, Success, Try}

class Controller @Inject()(override val state: GameState, override val colorFactory: ColorFactoryInterface) extends ControllerInterface with Publisher {

  private val undoManager = new UndoManager

  def difficultyMatcher(difficulty: String): Option[String] = difficulty match
    case "easy" => Some("easy")
    case "medium" => Some("medium")
    case "mastermind" => Some("mastermind")
    case _ => None


  def setDifficulty(difficultyInput: String): Unit = {
    val difficulty = difficultyMatcher(difficultyInput)
    Try(GameData(DifficultyStrategy.getAttempts(difficulty.get), colorFactory.pickSolution())) match {
      case Success(newGameData) =>
        publish(InGame(newGameData))
      case Failure(exception) =>
        print("Invalid Difficulty\n")
        print("Please use easy, medium or mastermind")
        print("\n")
    }
  }

  override def save(): Unit = {

  }

  override def load(): Unit = {
    publish(InGame(state.state))
  }

  override def addAttempt(input: String): Unit =
    val colors = input.split(" ").toVector
    Try(Attempt(colors.map(colorInput => colorFactory.getColor(colorInput).get))) match {
      case Success(filledSuccess) =>
        if filledSuccess.userPickedColors.size < 4 then
          print("Invalid Input\n")
        else
          val newState = undoManager.doStep(new AddCommand(state, filledSuccess))
          println(newState.solution)
          println(newState.getCurrentTurn)
          if newState.getCurrentTurn == -1 then
            publish(GameOver(newState))
          else if newState.getAttempt(newState.getCurrentTurn+1).getCorrectPositions(newState.solution) == 4 then
            publish(Win(newState))
          else
            publish(InGame(newState))

      case Failure(exception) =>
        print("Invalid Input\n")
        print("Please use those colors: ")
        colorFactory.getAllColors().foreach(shade => print(shade.toString + " "))
        print("\n")
    }


  override def undo(): Unit =
    println("TODO")
    //publish(InGame(undoManager.undoStep()))


  override def redo(): Unit =
    println("TODO")
    //publish(InGame(undoManager.redoStep()))

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

  def gameToString: String = {
    state.state.toString()
  }

}
