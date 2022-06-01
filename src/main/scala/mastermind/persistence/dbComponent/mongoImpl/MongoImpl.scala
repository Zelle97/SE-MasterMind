package mastermind.persistence.dbComponent.mongoImpl

import com.google.inject.{Guice, Inject}
import mastermind.MasterMindModule
import mastermind.core.model.gameDataComponent.gameDataBaseImpl.GameData
import mastermind.persistence.dbComponent.DaoInterface
import mastermind.persistence.fileIOComponent.fileIOJsonImpl.FileIO
import org.mongodb.scala.*
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.model.*
import org.mongodb.scala.model.Filters.*
import org.mongodb.scala.model.Updates.*
import org.mongodb.scala.model.UpdateOptions
import play.api.libs.json.{JsNumber}
import mastermind.MasterMindModule
import scala.concurrent.Await
import scala.concurrent.duration.Duration

import org.mongodb.scala.result.InsertOneResult

class MongoImpl extends DaoInterface {

  val uri: String = "mongodb://dbuser:dbpass@database:27017/mastermind?retryWrites=true&w=majority&authSource=admin"
  System.setProperty("org.mongodb.async.type", "netty")
  val client: MongoClient = MongoClient(uri)
  val db: MongoDatabase = client.getDatabase("mastermind")
  val fileIO = new FileIO()
  val collection: MongoCollection[Document] = db.getCollection("mastermind")
  val gameId: Int = 1

  def load: GameData = {
    val result = Await.result(collection.find(equal("id", gameId)).first().head(), Duration.Inf)
    fileIO.loadFromString(result.toJson())
  }

  def save(gameData: GameData): Unit = {
    val jsObject = fileIO.gameDataToJson(gameData) + ("id" -> JsNumber(gameId))
    val doc = Document(BsonDocument.apply(jsObject.toString))
    collection.insertOne(doc)
    collection.countDocuments(equal("id", gameId)).subscribe(new Observer[Long] {
      override def onSubscribe(subscription: Subscription): Unit = subscription.request(1)

      override def onNext(result: Long): Unit = if (result == 0) documentNotFound(doc) else documentFound(doc)

      override def onError(e: Throwable): Unit = println("Failed")

      override def onComplete(): Unit = println("Completed")
    })

  }

  def documentNotFound(doc: Document) = {
    collection.insertOne(doc).subscribe(new Observer[InsertOneResult] {

      override def onSubscribe(subscription: Subscription): Unit = subscription.request(1)

      override def onNext(result: InsertOneResult): Unit = println("Next...")

      override def onError(e: Throwable): Unit = println("New Insert Failed")

      override def onComplete(): Unit = println("New Insert Complete")
    })
  }

  def documentFound(doc: Document) = {
    collection
      .findOneAndReplace(equal("id", gameId), doc)
      .subscribe(new Observer[Any] {

        override def onSubscribe(subscription: Subscription): Unit = subscription.request(1)

        override def onNext(result: Any): Unit = println("Next...")

        override def onError(e: Throwable): Unit = println("Failed")

        override def onComplete(): Unit = println("Completed")
      })
  }
}
