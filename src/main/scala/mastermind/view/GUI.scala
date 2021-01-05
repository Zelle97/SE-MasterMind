package mastermind.view

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
    text = "<html><p>You are the codebreaker: <br><br>Trie to guess the pattern in order and color. <br>" +
      "There are three different difficulties: <br>" +
      "easy -> 10 turns <br>medium -> 8 turns <br> mastermind -> 7turns<br>" +
      "Each guess is made by placing a row of code pegs on the decoding board. " +
      "Once placed, you provide some feedback on the right side of the row with your guess. <br><br>" +
      "Good Luck!!</p></html>"
  }
  centerOnScreen()
}

class PopUp(titleString: String, label: String) extends Frame {
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

  val items = List("", "red", "blue", "green", "yellow", "black", "white", "orange", "brown")

  var color1 = new ComboBox(items)
  var color2 = new ComboBox(items)
  var color3 = new ComboBox(items)
  var color4 = new ComboBox(items)

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
            listenTo(controller)
            reactions += {
              case _: InGame =>
                text = controller.gameData.getAttempt(outerRow).getUserPickedColor(innerRow).colorString
            }
          }
        }
      }
      contents += new Label {
        border = LineBorder(java.awt.Color.BLACK, 1)
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
    contents += new Menu("readMe") {
      contents += new MenuItem(Action("manual") {
        new Manual
      })
    }
  }

  contents = new BorderPanel {
    add(gameboard, BorderPanel.Position.Center)
    add(new BorderPanel {
      add(colorSelectPanel, BorderPanel.Position.Center)
      add(new GridPanel(1, 2) {
        contents += Button("submit") {
          controller.addAttempt(
            color1.selection.item
              .concat(" " + color2.selection.item)
              .concat(" " + color3.selection.item)
              .concat(" " + color4.selection.item))
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
    case event: Win => new PopUp("Congratulations", "You won!")
    case event: GameOver => new PopUp("Game Over", "It seems like you lost... loser!")
  }

  def redraw = {
    repaint
  }

}
