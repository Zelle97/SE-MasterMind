package mastermind

import mastermind.MasterMind.gameField
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MasterMindSpec extends AnyWordSpec with Matchers{

  "The gameboard" when {
    "concating Title" should {
      "have a Title" in {
        Gameboard(gameField(), "").concatTitle().gamefield shouldBe "\t\t\t\tMaster Mind"
      }
    }
    "concating a vertical Line" should {
      "have a vertical Line" in {
        Gameboard(gameField(), "").concatVerticalLine().gamefield shouldBe "|"
      }
    }
    "concating a String" should {
      "have a String" in {
        Gameboard(gameField(), "").addString("Hallo").gamefield shouldBe "Hallo"
      }
    }
    "concating each Slotfield" should {
      "have a Slotfield" in {
        Gameboard(gameField(), "").forEachSlotfield(0,0).gamefield shouldBe "------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
    "concating each Slot" should {
      "have a slot" in {
        Gameboard(gameField(), "").forEachSlot(0,0).gamefield shouldBe "|          ||          ||          ||          |"
      }
    }
    "concating all Slotfields" should {
      "have all Slotfields" in {
        Gameboard(gameField(), "").concatGamefield().gamefield shouldBe "------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
    "concating a empty Slot" should {
      "have a empty Slot" in {
        Gameboard(gameField(), "").concatSlotPadding().gamefield shouldBe "|          ||          ||          ||          |"
      }
    }
    "concating a horizontal Line" should {
      "have a horizontal Line" in {
        Gameboard(gameField(), "").concatHorizontalLine().gamefield shouldBe "------------------------------------------------"
      }
    }
    "concating a Line break" should {
      "have a Line break" in {
        Gameboard(gameField(),"").concatLineBreak().gamefield shouldBe "\n"
      }
    }
    "concating empty Space" should {
      "have a empty Space" in {
        Gameboard(gameField(),"").concatEmptySpace().gamefield shouldBe "          "
      }
    }
    "concating the correct Positions" should {
      "have the correct Positions" in {
        Gameboard(gameField(),"").concatCorrectPositions().gamefield shouldBe " Correct Positions: 0"
      }
    }
    "concating the correct Colors" should {
      "have the correct Colors" in {
        Gameboard(gameField(),"").concatCorrectColors().gamefield shouldBe " Correct Colors: 0"
      }
    }
  }
  "The gamefield" when {
    "initialized" should {
      "contain 10 Elements" in {
        MasterMind.gameField().size shouldBe 10
      }
      "consist of empty Strings" in {
        MasterMind.gameField()(0)(0) shouldBe "          "
      }
    }
  }
  "The gameboard" when {
    "populated" should {
      "look like this" in {
        Gameboard(gameField(), "").gameFieldString().gamefield shouldBe "\t\t\t\tMaster Mind\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
  }

}
