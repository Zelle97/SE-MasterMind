
package mastermind.model

import mastermind.controllerComponent.DifficultyStrategy
import mastermind.model
import mastermind.model.colorComponent.colorBaseImpl.Color
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameBoardSpec extends AnyWordSpec with Matchers {
  val solution = Color().pickSolution()
  val attempts = DifficultyStrategy.getAttempts("easy")
  "The gameboard" when {
    "concating Title" should {
      "have a Title" in {
        GameBoard(GameData(attempts, solution)).concatTitle() shouldBe "\t\t\t\tMaster Mind"
      }
    }
    "concating a vertical Line" should {
      "have a vertical Line" in {
        model.GameBoard(GameData(attempts, solution), "").concatVerticalLine() shouldBe "|"
      }
    }
    "concating each Slot" should {
      "have a slot" in {
        model.GameBoard(GameData(attempts, solution), "").forEachSlot(0) shouldBe "|          ||          ||          ||          |"
      }
    }
    "concating all Slotfields" should {
      "have all Slotfields" in {
        model.GameBoard(GameData(attempts, solution), "").concatEachSlotField() shouldBe "------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
    "concating a empty Slot" should {
      "have a empty Slot" in {
        model.GameBoard(GameData(attempts, solution), "").concatSlotPadding() shouldBe "|          ||          ||          ||          |"
      }
    }
    "concating a horizontal Line" should {
      "have a horizontal Line" in {
        model.GameBoard(GameData(attempts, solution), "").concatHorizontalLine() shouldBe "------------------------------------------------"
      }
    }
    "concating a Line break" should {
      "have a Line break" in {
        model.GameBoard(GameData(attempts, solution), "").concatLineBreak() shouldBe "\n"
      }
    }
    "concating empty Space" should {
      "have a empty Space" in {
        model.GameBoard(GameData(attempts, solution), "").concatEmptySpace() shouldBe "          "
      }
    }
    "concating the correct Positions" should {
      "have the correct Positions" in {
        model.GameBoard(GameData(attempts, solution), "").concatCorrectPositions(0) shouldBe " Correct Positions: 0"
      }
    }
    "concating the correct Colors" should {
      "have the correct Colors" in {
        model.GameBoard(GameData(attempts, solution), "").concatCorrectColors(0) shouldBe " Correct Colors: 0"
      }
    }
  }
  "The gameboard" when {
    "populated" should {
      "look like this" in {
        model.GameBoard(GameData(attempts, solution), "").gameToString().gamefield shouldBe "\t\t\t\tMaster Mind\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
  }

}

