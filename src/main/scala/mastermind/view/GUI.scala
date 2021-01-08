package mastermind.view

import java.awt.Color

import javax.swing.border.Border
import mastermind.controllerComponent.GameState
import mastermind.controllerComponent.controllerBaseImpl.Controller
import mastermind.util.{GameOver, InGame, Win}

import scala.swing.Swing.{LineBorder}
import scala.swing.{Action, BorderPanel, Button, ComboBox, Dialog, Dimension, FlowPanel, Frame, GridPanel, Label, Menu, MenuBar, MenuItem}
import scala.util.{Failure, Success, Try}

class Manual extends Frame {
  title = "Manual"
  resizable = false
  visible = true
  preferredSize = new Dimension(600, 250)
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
  title = "Warning"
  resizable = false
  visible = true
  preferredSize = new Dimension(300, 150)
  contents = new Label {
    text = "<html><p> <h2 style=\"color: rgb(255, 0  , 0  )\"> Chose all four colors <br> Good Luck!!</p></html>"
  }
  centerOnScreen()
}

class PopUpEnd(titleString: String, label: String, controller: Controller) extends Dialog {
  title = titleString
  resizable = false
  visible = true
  preferredSize = new Dimension(400, 80)
  modal = true
  contents = new FlowPanel() {
    contents += new Label(label)
    contents += Button("Exit") {
      sys.exit(0)
    }
    contents += Button("New Game") {
      new PopUpNewGame(controller)
      close()
    }
  }
  centerOnScreen()
}

class PopUpNewGame(controller: Controller)extends Dialog {
  title = "new Game"
  resizable = false
  visible = true
  preferredSize = new Dimension(400, 80)
  contents = new FlowPanel() {
    contents += new Label("Chose your difficulty")
    contents += Button("easy") {
      controller.setDifficulty("easy")
      close()
    }
    contents += Button("medium") {
      controller.setDifficulty("medium")
      close()
    }
    contents += Button("mastermind") {
      controller.setDifficulty("mastermind")
      close()
    }
  }
  centerOnScreen()
}

class GUI(controller: Controller) extends Frame {
  listenTo(controller)

  title = "HTWG Mastermind"
  preferredSize = new Dimension(400 * 2, 240 * 4)
  val items = List(
    "Chose a color",
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

  var gameboard: GridPanel = new GridPanel(controller.gameData.getAttemptSize(), 1) {
    background = backgroundColor
    for {
      outerRow <- controller.gameData.getAllAttempts().indices   //size-1 to 0 by -1
    } {
      contents += new GridPanel(1, 5) {
        background = backgroundColor
        for {
          innerRow <- controller.gameData.getAttempt(0).getAllUserColors().indices  //size-1 to 0 by -1
        } {
          contents += new Label {
            text = controller.gameData.getAttempt(outerRow).getUserPickedColor(innerRow).colorString
            background = new Color(240, 240, 240)
            border = borderColor
            opaque = true
            listenTo(controller)
            reactions += {
              case _: InGame | _: Win | _: GameOver =>
                Try(controller.gameData.getAttempt(outerRow).getUserPickedColor(innerRow).colorString) match {
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
          .concat(controller.gameData.getAttempt(outerRow).getCorrectPositions(controller.gameData.getSolution()).toString)
          .concat("<br>")
          .concat("Correct Colors: ")
          .concat(controller.gameData.getAttempt(outerRow).getCorrectColors(controller.gameData.getSolution()).toString)
        listenTo(controller)
        reactions += {
          case _: InGame =>
            Try(controller.gameData.getAttempt(outerRow).getUserPickedColor(0).colorString) match {
              case Success(color) =>
                background = new Color(255, 204, 51)
                foreground = java.awt.Color.BLACK
                border = borderColor
                text = "<html>Correct Positions: "
                  .concat(controller.gameData.getAttempt(outerRow).getCorrectPositions(controller.gameData.getSolution()).toString)
                  .concat("<br>")
                  .concat("Correct Colors: ")
                  .concat(controller.gameData.getAttempt(outerRow).getCorrectColors(controller.gameData.getSolution()).toString)
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
    }
    contents += new Menu("Game") {
      contents += new MenuItem(Action("new Game") {
        new PopUpNewGame(controller)
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
          if (color1.selection.item == "Chose a color" || color2.selection.item == "Chose a color" ||
            color3.selection.item == "Chose a color" || color4.selection.item == "Chose a color") {
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
  new PopUpNewGame(controller)
  centerOnScreen()
  visible = true
  resizable = false
  redraw


  reactions += {
    case event: InGame => redraw
    case event: Win => new PopUpEnd("Congratulations", "You won!", this.controller)
    case event: GameOver => new PopUpEnd("Game Over", "It seems like you lost... loser!", this.controller)
  }

  def redraw = {
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
