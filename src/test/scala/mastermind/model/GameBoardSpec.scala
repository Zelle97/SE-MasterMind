package mastermind.model

import mastermind.controller.ColorPicker
import mastermind.model
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameBoardSpec extends AnyWordSpec with Matchers {
  val solution = ColorPicker().pickSolution()
  "The gameboard" when {
    "concating Title" should {
      "have a Title" in {
        GameBoard(GameData(solution = solution), "").concatTitle().gamefield shouldBe "\t\t\t\tMaster Mind"
      }
    }
    "concating a vertical Line" should {
      "have a vertical Line" in {
        model.GameBoard(GameData(solution = solution), "").concatVerticalLine().gamefield shouldBe "|"
      }
    }
    "concating a String" should {
      "have a String" in {
        model.GameBoard(GameData(solution = solution), "").addString("Hallo").gamefield shouldBe "Hallo"
      }
    }
    "concating each Slotfield" should {
      "have a Slotfield" in {
        model.GameBoard(GameData(solution = solution), "").forEachSlotfield(0).gamefield shouldBe "------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
    "concating each Slot" should {
      "have a slot" in {
        model.GameBoard(GameData(solution = solution), "").forEachSlot(0).gamefield shouldBe "|          ||          ||          ||          |"
      }
    }
    "concating all Slotfields" should {
      "have all Slotfields" in {
        model.GameBoard(GameData(solution = solution), "").concatGamefield().gamefield shouldBe "------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
    "concating a empty Slot" should {
      "have a empty Slot" in {
        model.GameBoard(GameData(solution = solution), "").concatSlotPadding().gamefield shouldBe "|          ||          ||          ||          |"
      }
    }
    "concating a horizontal Line" should {
      "have a horizontal Line" in {
        model.GameBoard(GameData(solution = solution), "").concatHorizontalLine().gamefield shouldBe "------------------------------------------------"
      }
    }
    "concating a Line break" should {
      "have a Line break" in {
        model.GameBoard(GameData(solution = solution), "").concatLineBreak().gamefield shouldBe "\n"
      }
    }
    "concating empty Space" should {
      "have a empty Space" in {
        model.GameBoard(GameData(solution = solution), "").concatEmptySpace().gamefield shouldBe "          "
      }
    }
    "concating the correct Positions" should {
      "have the correct Positions" in {
        model.GameBoard(GameData(solution = solution), "").concatCorrectPositions(0).gamefield shouldBe " Correct Positions: 0"
      }
    }
    "concating the correct Colors" should {
      "have the correct Colors" in {
        model.GameBoard(GameData(solution = solution), "").concatCorrectColors(0).gamefield shouldBe " Correct Colors: 0"
      }
    }
  }
  "The gameboard" when {
    "populated" should {
      "look like this" in {
        model.GameBoard(GameData(solution = solution), "").gameFieldString().gamefield shouldBe "\t\t\t\tMaster Mind\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
  }

}