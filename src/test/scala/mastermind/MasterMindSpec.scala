package mastermind

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MasterMindSpec extends AnyWordSpec with Matchers{

  "The gameboard" when {
    var gameboard = ""
    "concating Title" should {
      "have a Title" in {
        MasterMind.concatTitle(gameboard) shouldBe("\t\t\t\tMaster Mind")
      }
    }
    "concating a vertical Line" should {
      "have a vertical Line" in {
        MasterMind.concatVerticalLine(gameboard) shouldBe("|")
      }
    }
    "concating a empty Slot" should {
      "have a empty Slot" in {
        MasterMind.concatSlotPadding(gameboard) shouldBe("|          ||          ||          ||          |")
      }
    }
    "concating a horizontal Line" should {
      "have a horizontal Line" in {
        MasterMind.concatHorizontalLine(gameboard) shouldBe("------------------------------------------------")
      }
    }
    "concating a Line break" should {
      "have a Line break" in {
        MasterMind.concatLineBreak(gameboard) shouldBe("\n")
      }
    }
    "concating empty Space" should {
      "have a empty Space" in {
        MasterMind.concatEmptySpace(gameboard) shouldBe("          ")
      }
    }
    "concating the correct Positions" should {
      "have the correct Positions" in {
        MasterMind.concatCorrectPositions(gameboard) shouldBe(" Correct Positions: 0")
      }
    }
    "concating the correct Colors" should {
      "have the correct Colors" in {
        MasterMind.concatCorrectColors(gameboard) shouldBe(" Correct Colors: 0")
      }
    }
  }
  "The gamefield" when {
    "initialized" should {
      "contain 10 Elements" in {
        MasterMind.gameField().size shouldBe(10)
      }
      "consist of empty Strings" in {
        MasterMind.gameField()(0)(0) shouldBe "          "
      }
    }
  }
  "The gameboard" when {
    "populated" should {
      val gameData = MasterMind.gameField()
      "look like this" in {
        MasterMind.gameFieldString(gameData) shouldBe("\t\t\t\tMaster Mind\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n")
      }
    }
  }

}
