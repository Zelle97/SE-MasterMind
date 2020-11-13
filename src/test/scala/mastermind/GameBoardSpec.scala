package mastermind

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameBoardSpec extends AnyWordSpec with Matchers {

  "The gameboard" when {
    "concating Title" should {
      "have a Title" in {
        GameBoard(GameData(), "").concatTitle().gamefield shouldBe "\t\t\t\tMaster Mind"
      }
    }
    "concating a vertical Line" should {
      "have a vertical Line" in {
        GameBoard(GameData(), "").concatVerticalLine().gamefield shouldBe "|"
      }
    }
    "concating a String" should {
      "have a String" in {
        GameBoard(GameData(), "").addString("Hallo").gamefield shouldBe "Hallo"
      }
    }
    "concating each Slotfield" should {
      "have a Slotfield" in {
        GameBoard(GameData(), "").forEachSlotfield(0).gamefield shouldBe "------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
    "concating each Slot" should {
      "have a slot" in {
        GameBoard(GameData(), "").forEachSlot(0).gamefield shouldBe "|          ||          ||          ||          |"
      }
    }
    "concating all Slotfields" should {
      "have all Slotfields" in {
        GameBoard(GameData(), "").concatGamefield().gamefield shouldBe "------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
    "concating a empty Slot" should {
      "have a empty Slot" in {
        GameBoard(GameData(), "").concatSlotPadding().gamefield shouldBe "|          ||          ||          ||          |"
      }
    }
    "concating a horizontal Line" should {
      "have a horizontal Line" in {
        GameBoard(GameData(), "").concatHorizontalLine().gamefield shouldBe "------------------------------------------------"
      }
    }
    "concating a Line break" should {
      "have a Line break" in {
        GameBoard(GameData(), "").concatLineBreak().gamefield shouldBe "\n"
      }
    }
    "concating empty Space" should {
      "have a empty Space" in {
        GameBoard(GameData(), "").concatEmptySpace().gamefield shouldBe "          "
      }
    }
    "concating the correct Positions" should {
      "have the correct Positions" in {
        GameBoard(GameData(), "").concatCorrectPositions().gamefield shouldBe " Correct Positions: 0"
      }
    }
    "concating the correct Colors" should {
      "have the correct Colors" in {
        GameBoard(GameData(), "").concatCorrectColors().gamefield shouldBe " Correct Colors: 0"
      }
    }
  }
  "The gameboard" when {
    "populated" should {
      "look like this" in {
        GameBoard(GameData(), "").gameFieldString().gamefield shouldBe "\t\t\t\tMaster Mind\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
  }

}
