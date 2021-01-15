package mastermind.view

import java.awt.Color

import javax.swing.border.Border
import mastermind.controllerComponent.{ControllerInterface, GameState}
import mastermind.controllerComponent.controllerBaseImpl.Controller
import mastermind.util.{GameOver, InGame, Win}

import scala.swing.Swing.LineBorder
import scala.swing.{Action, BorderPanel, Button, ComboBox, Dialog, Dimension, FlowPanel, Frame, GridPanel, Label, Menu, MenuBar, MenuItem}
import scala.util.{Failure, Success, Try}

class Manual extends Frame {
  title = "MasterMind - Manual"
  resizable = false
  visible = true
  preferredSize = new Dimension(300, 300)
  contents = new Label {
    text = "<html><p>You are the codebreaker: <br><br>Try to guess the pattern in order and color. <br>" +
      "There are three different difficulties: <br>" +
      "easy -> 10 turns <br>medium -> 8 turns <br> mastermind -> 7turns<br>" +
      "Each guess is made by placing a row of code pegs on the decoding board. " +
      "Once placed, you are provided with some feedback on the right side of the row with your guess. <br><br>" +
      "Good Luck!!</p></html>"
  }
  centerOnScreen()
}

class Warning extends Dialog {
  title = "MasterMind - Warning"
  resizable = false
  visible = true
  preferredSize = new Dimension(300, 150)
  contents = new Label {
    text = "<html><p> <h2 style=\"color: rgb(255, 0  , 0  )\"> Choose all four colors before you submit! <br> Good Luck!</p></html>"
  }
  centerOnScreen()
}

class PopUpEnd(titleString: String, label: String, controller: ControllerInterface, parentFrame: Frame) extends Frame {
  val frame: PopUpEnd = this
  for(content <- parentFrame.menuBar.contents){
    content.enabled = false
  }
  title = titleString
  resizable = false
  visible = true
  preferredSize = new Dimension(500, 100)
  contents = new GridPanel(2, 1) {
    contents += new Label(label)
    contents += new GridPanel(1, 2)
    contents += Button("New Game") {
      new PopUpNewGame(controller, parentFrame)
      close()
      for(content <- parentFrame.menuBar.contents){
        content.enabled = true
      }
    }
    contents += Button("Exit") {
      sys.exit(0)
    }

  }
  centerOnScreen()
}

class PopUpNewGame(controller: ControllerInterface, parentFrame: Frame)extends Frame {
  parentFrame.visible = false
  title = "New Game"
  resizable = false
  visible = true
  preferredSize = new Dimension(200, 250)

  val buttonEasy: Button = Button("Easy"){
    controller.setDifficulty("easy")
    parentFrame.visible = true
    close()
  }
  buttonEasy.background = new Color(173, 255, 47)

  val buttonMedium :Button = Button("Medium") {
    controller.setDifficulty("medium")
    parentFrame.visible = true
    close()
  }
  buttonMedium.background = new Color(0, 255, 0)

  val buttonMasterMind :Button = Button("MasterMind") {
    controller.setDifficulty("mastermind")
    parentFrame.visible = true
    close()
  }
  buttonMasterMind.background = new Color(50, 205, 50)



  contents = new GridPanel(4, 1) {
    contents += new Label("What's your Level?")

      contents += buttonEasy
      contents += buttonMedium
      contents += buttonMasterMind
  }
  centerOnScreen()
}

class GUI(controller: ControllerInterface) extends Frame {
  listenTo(controller)
  val frame: GUI = this
  title = "MasterMind"
  preferredSize = new Dimension(800, 600)
  val items = List(
    "Choose a color",
    "<html><h2 style=\"background-color: rgb(255, 0  , 0  ); color: rgb(255, 0  , 0  )\"> &emsp &emsp &emsp red",
    "<html><h2 style=\"background-color: rgb(51 , 153, 255); color: rgb(51 , 153, 255)\"> &emsp &emsp &emsp blu",
    "<html><h2 style=\"background-color: rgb(0  , 204, 0  ); color: rgb(0  , 204, 0  )\"> &emsp &emsp &emsp gre",
    "<html><h2 style=\"background-color: rgb(255, 255, 0  ); color: rgb(255, 255, 0  )\"> &emsp &emsp &emsp yel",
    "<html><h2 style=\"background-color: rgb(51 , 51 , 51 ); color: rgb(51 , 51 , 51 )\"> &emsp &emsp &emsp bla",
    "<html><h2 style=\"background-color: rgb(255, 255, 255); color: rgb(255, 255, 255)\"> &emsp &emsp &emsp whi",
    "<html><h2 style=\"background-color: rgb(255, 102, 0  ); color: rgb(255, 102, 0  )\"> &emsp &emsp &emsp ora",
    "<html><h2 style=\"background-color: rgb(102, 51 , 0  ); color: rgb(102, 51 , 0  )\"> &emsp &emsp &emsp bro")

  val backgroundColor = new Color(80, 80, 80)
  val borderColor: Border = LineBorder(backgroundColor, 1)

  var color1 = new ComboBox(items)
  color1.maximumRowCount = 9
  var color2 = new ComboBox(items)
  color2.maximumRowCount = 9
  var color3 = new ComboBox(items)
  color3.maximumRowCount = 9
  var color4 = new ComboBox(items)
  color4.maximumRowCount = 9

  var colorSelectPanel: FlowPanel = new FlowPanel {
    contents += color1
    contents += color2
    contents += color3
    contents += color4
    background = backgroundColor
  }


  var gameboard: GridPanel = new GridPanel(controller.getGameData().getAttemptSize(), 1) {
    background = backgroundColor
    for {
      outerRow <- controller.getGameData().getAllAttempts().indices   //size-1 to 0 by -1
    } {
      contents += new GridPanel(1, 5) {
        background = backgroundColor
        for {
          innerRow <- controller.getGameData().getAttempt(0).getAllUserColors().indices  //size-1 to 0 by -1
        } {
          contents += new Label {
            text = controller.getGameData().getAttempt(outerRow).getUserPickedColor(innerRow).colorString
            background = new Color(240, 240, 240)
            border = borderColor
            opaque = true
            listenTo(controller)
            reactions += {
              case _: InGame | _: Win | _: GameOver =>
                Try(controller.getGameData().getAttempt(outerRow).getUserPickedColor(innerRow).colorString) match {
                  case Success(color) =>
                    background = new Color(240, 240, 240)
                    border = borderColor
                    color match {
                      case "red" => background = new Color(255, 0, 0)
                      case "blue" => background = new Color(51, 153, 255)
                      case "green" => background = new Color(0, 204, 0)
                      case "yellow" => background = new Color(255, 255, 0)
                      case "black" => background = new Color(51, 51, 51)
                      case "white" => background = new Color(255, 255, 255)
                      case "orange" => background = new Color(255, 102, 0)
                      case "brown" => background = new Color(102, 51, 0)
                      case _ => background = new Color(240, 240, 240)
                    }
                  case Failure(exception) =>
                    background = backgroundColor
                    border = LineBorder(backgroundColor, 1)
                }
            }
          }
        }
      }
      contents += new Label {
        border = borderColor
        background = new Color(255, 204, 51)
        border = borderColor
        opaque = true
        text = "<html>Correct Positions: "
          .concat(controller.getGameData().getAttempt(outerRow).getCorrectPositions(controller.getGameData().getSolution()).toString)
          .concat("<br>")
          .concat("Correct Colors: ")
          .concat(controller.getGameData().getAttempt(outerRow).getCorrectColors(controller.getGameData().getSolution()).toString)
        listenTo(controller)
        reactions += {
          case _: InGame | _:Win | _:GameOver =>
            Try(controller.getGameData().getAttempt(outerRow).getUserPickedColor(0).colorString) match {
              case Success(color) =>
                background = new Color(255, 204, 51)
                foreground = java.awt.Color.BLACK
                border = borderColor
                text = "<html>Correct Positions: "
                  .concat(controller.getGameData().getAttempt(outerRow).getCorrectPositions(controller.getGameData().getSolution()).toString)
                  .concat("<br>")
                  .concat("Correct Colors: ")
                  .concat(controller.getGameData().getAttempt(outerRow).getCorrectColors(controller.getGameData().getSolution()).toString)
              case Failure(exception) =>
                background = backgroundColor
                foreground = backgroundColor
                border = LineBorder(backgroundColor, 1)
            }
        }
      }
    }
  }

  menuBar = new MenuBar {
    contents += new Menu("ReadMe") {
      contents += new MenuItem(Action("Manual") {
        new Manual
      })
    }

    contents += new Menu("Actions") {
      contents += new MenuItem(Action("Undo") {
        controller.undo()
      })
      contents += new MenuItem(Action("Redo") {
        controller.redo()
      })
      contents += new MenuItem(Action("Save") {
        controller.save()
      })
      contents += new MenuItem(Action("Load") {
        controller.load()
      })
    }
    contents += new Menu("New Game") {
      contents += new MenuItem(Action("Easy") {
        controller.setDifficulty("easy")
      })
      contents += new MenuItem(Action("Medium") {
        controller.setDifficulty("medium")
      })
      contents += new MenuItem(Action("MasterMind") {
        controller.setDifficulty("mastermind")
      })
    }
  }

  contents = new BorderPanel {
    add(gameboard, BorderPanel.Position.Center)
    add(new BorderPanel {
      add(colorSelectPanel, BorderPanel.Position.Center)
      add(new GridPanel(1, 2) {
        background = backgroundColor
        contents += Button("submit") {
          enabled = true
          if (color1.selection.item == "Choose a color" || color2.selection.item == "Choose a color" ||
            color3.selection.item == "Choose a color" || color4.selection.item == "Choose a color") {
            new Warning
          } else if (GameState.state.equals("I am in Game")) {
            controller.addAttempt(
              getColor(color1.selection.item.substring(101))
                .concat(" " + getColor(color2.selection.item.substring(101)))
                .concat(" " + getColor(color3.selection.item.substring(101)))
                .concat(" " + getColor(color4.selection.item.substring(101))))
          } else {
            enabled = false
          }
        }
        contents +=
          Button("exit") {
            sys.exit(0)
          }
      }, BorderPanel.Position.South)
    }, BorderPanel.Position.South)
    background = backgroundColor
  }
  centerOnScreen()
  resizable = false
  redraw()
  new PopUpNewGame(controller, frame)

  reactions += {
    case event: InGame => redraw()
    case event: Win => new PopUpEnd("Congratulations", "You are a true MasterMind!!", this.controller, frame)
    case event: GameOver => new PopUpEnd("Game Over", "You lost the game!! try again?", this.controller, frame)
  }

  def redraw(): Unit = {
    repaint
  }

  def getColor(input: String): String = {
    input match {
      case "red" => "red"
      case "blu" => "blue"
      case "gre" => "green"
      case "yel" => "yellow"
      case "bla" => "black"
      case "whi" => "white"
      case "ora" => "orange"
      case "bro" => "brown"
    }
  }
}
