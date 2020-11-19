package mastermind

import mastermind.model.{Attempt, GameData}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.immutable.Vector

class GameDataSpec extends AnyWordSpec with Matchers {
  "The Game Data" when {
    "created" should {
      "have an empty Vector" in {
        GameData().attempts shouldBe a[Vector[_]]
      }
    }
    "initialized empty" should {
      "contain empty 10 Attemps" in {
        GameData().attempts.size shouldBe 10
      }
    }
    "a attempt is added" should {
      "contain that attempt" in {
        GameData(Vector()).addAttempt(Attempt()).attempts.size shouldBe 1
      }
    }
    "a attempt is updated" should {
      "contain the updated attempt" in {
        GameData().addAttempt(Attempt()).updateAttempt(0, Attempt(Vector("test"))).attempts(0).userPickedColors(0) shouldBe "test"
      }
    }
  }

}
