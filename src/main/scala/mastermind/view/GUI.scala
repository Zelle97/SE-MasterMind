package mastermind.view

import mastermind.controller.Controller
import mastermind.util.{GameOver, InGame, Win}

import scala.swing.Swing.LineBorder
import scala.swing.{BorderPanel, BoxPanel, Button, ComboBox, Dimension, FlowPanel, Frame, GridPanel, Label, Orientation}

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

  var gameboard: GridPanel = new GridPanel(controller.gameData.attempts.size, 1) {
    for {
      outerRow <- controller.gameData.attempts.indices
    } {
      contents += new GridPanel(1, 5) {
        for {
          innerRow <- controller.gameData.attempts(0).userPickedColors.indices
        } {
          contents += new Label {
            border = LineBorder(java.awt.Color.BLACK, 1)
            text = controller.gameData.attempts(outerRow).userPickedColors(innerRow).colorString
            listenTo(controller)
            reactions += {
              case _: InGame =>
                text = controller.gameData.attempts(outerRow).userPickedColors(innerRow).colorString
            }
          }
        }
      }
      contents += new Label {
        text = "Correct Positions: "
          .concat(controller.gameData.attempts(outerRow).getCorrectPositions(controller.gameData.solution).toString)
          .concat("\n")
          .concat("Correct Colors: ")
          .concat(controller.gameData.attempts(outerRow).getCorrectColors(controller.gameData.solution).toString)
        listenTo(controller)
        reactions += {
          case _: InGame =>
            text = "Correct Positions: "
              .concat(controller.gameData.attempts(outerRow).getCorrectPositions(controller.gameData.solution).toString)
              .concat("\n")
              .concat("Correct Colors: ")
              .concat(controller.gameData.attempts(outerRow).getCorrectColors(controller.gameData.solution).toString)
        }
      }
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
