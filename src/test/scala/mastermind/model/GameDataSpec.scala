package mastermind.model

import mastermind.controllerComponent.DifficultyStrategy
import mastermind.model.attemptComponent.attemptBaseImpl.Attempt
import mastermind.model.colorComponent.colorBaseImpl.Color
import mastermind.model.gameDataComponent.gameDataBaseImpl.GameData
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.immutable.Vector

class GameDataSpec extends AnyWordSpec with Matchers {
  "The Game Data" when {
    val solution = Color().pickSolution()
    val attempts = DifficultyStrategy.getAttempts("easy")

    "created" should {
      "have an empty Vector" in {
        GameData(attempts, solution).attempts shouldBe a[Vector[_]]
      }
    }
    "initialized empty" should {
      "contain empty 10 Attemps" in {
        GameData(attempts, solution).attempts.size shouldBe 10
      }
    }
    "a attempt is updated" should {
      "contain the updated attempt" in {
        GameData(attempts, solution).updateAttempt(0, Attempt(Vector(Color().apply("red").get))).attempts(0).getUserPickedColor(0).getColor shouldBe "       red"
      }
    }
    "using toString" should {
      "get printed" in {
        GameData(attempts, solution).toString() shouldBe "\t\t\t\tMaster Mind\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
    "using getTurn()" should {
      "return the turn" in {
        GameData(attempts, solution, turn = 5).getTurn() shouldBe 5
      }
    }
    "using setTurn()" should {
      "set the turn" in {
        val gd = GameData(attempts, solution, turn = 5)
        gd.setTurn(20)
        gd.getTurn() shouldBe 20
      }
    }
  }
}
