package mastermind.view

import java.awt.Color

import mastermind.controller.Controller
import mastermind.util.{GameOver, InGame, Win}

import scala.swing.Swing.LineBorder
import scala.swing.{Action, BorderPanel, BoxPanel, Button, ComboBox, Dimension, FlowPanel, Frame, GridPanel, Label, Menu, MenuBar, MenuItem, Orientation}

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
      "Once placed, you provide some feedback on the right side of the row with your guess. <br><br>" +
      "Good Luck!!</p></html>"
  }
  centerOnScreen()
}

class Warning extends Frame {
  title = "Warning"
  resizable = false
  visible = true
  preferredSize = new Dimension(300, 150)
  contents = new Label {
    text = "<html><p> <h2 style=\"color: rgb(255, 0  , 0  )\"> Chose all four colors <br> Good Luck!!</p></html>"
  }
  centerOnScreen()
}

class PopUpEnd(titleString: String, label: String) extends Frame {
  title = titleString
  resizable = false
  visible = true
  preferredSize = new Dimension(400, 80)

  contents = new FlowPanel() {
    contents += new Label(label)
    contents += Button("Exit") {
      sys.exit(0)
    }
    contents += Button("New Game") {
    }
  }
  centerOnScreen()
}

class GUI(controller: Controller) extends Frame {

  listenTo(controller)

  title = "HTWG Mastermind"
  preferredSize = new Dimension(320 * 2, 240 * 4)

  val items = List(
    "Wähle eine Farbe",
    "<html><h2 style=\"background-color: rgb(255, 0  , 0  ); color: rgb(255, 0  , 0  )\"> &emsp &emsp &emsp red",
    "<html><h2 style=\"background-color: rgb(51 , 153, 255); color: rgb(51 , 153, 255)\"> &emsp &emsp &emsp blu",
    "<html><h2 style=\"background-color: rgb(0  , 204, 0  ); color: rgb(0  , 204, 0  )\"> &emsp &emsp &emsp gre",
    "<html><h2 style=\"background-color: rgb(255, 255, 0  ); color: rgb(255, 255, 0  )\"> &emsp &emsp &emsp yel",
    "<html><h2 style=\"background-color: rgb(51 , 51 , 51 ); color: rgb(51 , 51 , 51 )\"> &emsp &emsp &emsp bla",
    "<html><h2 style=\"background-color: rgb(255, 255, 255); color: rgb(255, 255, 255)\"> &emsp &emsp &emsp whi",
    "<html><h2 style=\"background-color: rgb(255, 102, 0  ); color: rgb(255, 102, 0  )\"> &emsp &emsp &emsp ora",
    "<html><h2 style=\"background-color: rgb(102, 51 , 0  ); color: rgb(102, 51 , 0  )\"> &emsp &emsp &emsp bro")

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
  }

  var gameboard: GridPanel = new GridPanel(controller.gameData.getAttemptSize(), 1) {
    for {
      outerRow <- controller.gameData.getAllAttempts().indices
    } {
      contents += new GridPanel(1, 5) {
        for {
          innerRow <- controller.gameData.getAttempt(0).getAllUserColors().indices
        } {
          contents += new Label {
            border = LineBorder(java.awt.Color.BLACK, 1)
            text = controller.gameData.getAttempt(outerRow).getUserPickedColor(innerRow).colorString
            background = new Color(204,204,204)
            opaque = true
            listenTo(controller)
            reactions += {
              case _: InGame =>
                //text = controller.gameData.getAttempt(outerRow).getUserPickedColor(innerRow).colorString
                controller.gameData.getAttempt(outerRow).getUserPickedColor(innerRow).colorString match{
                  case "red" => background = new Color(255, 0, 0)
                  case "blue" => background = new Color(51,153,255)
                  case "green" => background = new Color(0,204,0)
                  case "yellow" => background = new Color(255,255,0)
                  case "black" => background = new Color(51,51,51)
                  case "white" => background = new Color(255,255,255)
                  case "orange" => background = new Color(255,102,0)
                  case "brown" => background = new Color(102,51,0)
                  case _ => background = new Color(204,204,204)
                }
            }
          }
        }
      }
      contents += new Label {
        border = LineBorder(java.awt.Color.BLACK, 1)
        background = new Color(255,204,51)
        opaque = true
        text = "<html>Correct Positions: "
          .concat(controller.gameData.getAttempt(outerRow).getCorrectPositions(controller.gameData.getSolution()).toString)
          .concat("<br>")
          .concat("Correct Colors: ")
          .concat(controller.gameData.getAttempt(outerRow).getCorrectColors(controller.gameData.getSolution()).toString)
        listenTo(controller)
        reactions += {
          case _: InGame =>
            text = "<html>Correct Positions: "
              .concat(controller.gameData.getAttempt(outerRow).getCorrectPositions(controller.gameData.getSolution()).toString)
              .concat("<br>")
              .concat("Correct Colors: ")
              .concat(controller.gameData.getAttempt(outerRow).getCorrectColors(controller.gameData.getSolution()).toString)
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
        controller.undo()
      })
    }
  }

  contents = new BorderPanel {
    add(gameboard, BorderPanel.Position.Center)
    add(new BorderPanel {
      add(colorSelectPanel, BorderPanel.Position.Center)
      add(new GridPanel(1, 2) {
        contents += Button("submit") {
          if(color1.selection.item == "Wähle eine Farbe" || color2.selection.item == "Wähle eine Farbe"
            || color3.selection.item == "Wähle eine Farbe"|| color4.selection.item == "Wähle eine Farbe"){
            new Warning
          }else {
            controller.addAttempt(
              getColor(color1.selection.item.substring(101))
                .concat(" " + getColor(color2.selection.item.substring(101)))
                .concat(" " + getColor(color3.selection.item.substring(101)))
                .concat(" " + getColor(color4.selection.item.substring(101))))
          }
        }
        contents +=
          Button("exit") {
            sys.exit(0)
          }
      }, BorderPanel.Position.South)
    }, BorderPanel.Position.South)
  }
  centerOnScreen()
  visible = true
  resizable = false
  redraw

  reactions += {
    case event: InGame => redraw
    case event: Win => new PopUpEnd("Congratulations", "You won!")
    case event: GameOver => new PopUpEnd("Game Over", "It seems like you lost... loser!")
  }

  def redraw = {
    repaint
  }

  def getColor(input: String) :String = {
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
