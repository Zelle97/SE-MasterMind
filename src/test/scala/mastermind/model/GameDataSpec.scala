package mastermind.model

import mastermind.controller.ColorPicker
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.immutable.Vector

class GameDataSpec extends AnyWordSpec with Matchers {
  "The Game Data" when {
    val solution = ColorPicker().pickSolution()
    "created" should {
      "have an empty Vector" in {
        GameData(solution = solution).attempts shouldBe a[Vector[_]]
      }
    }
    "initialized empty" should {
      "contain empty 10 Attemps" in {
        GameData(solution = solution).attempts.size shouldBe 10
      }
    }
    "a attempt is added" should {
      "contain that attempt" in {
        GameData(Vector(), solution = solution).addAttempt(Attempt()).attempts.size shouldBe 1
      }
    }
    "a attempt is updated" should {
      "contain the updated attempt" in {
        GameData(solution = solution).addAttempt(Attempt()).updateAttempt(0, Attempt(Vector(Color("test")))).attempts(0).userPickedColors(0).color shouldBe "test"
      }
    }
    "using toString" should {
      "get printed" in {
        GameData(solution = solution).toString() shouldBe "\t\t\t\tMaster Mind\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n------------------------------------------------\n|          ||          ||          ||          | Correct Colors: 0\n|          ||          ||          ||          |\n|          ||          ||          ||          | Correct Positions: 0\n------------------------------------------------\n"
      }
    }
  }

}
