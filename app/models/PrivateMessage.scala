package models

import com.mongodb.casbah.Imports._
import java.util.Date
import com.mongodb.casbah.MongoCollection

case class PrivateMessage(id: ObjectId = new ObjectId,
  title: String,
  content: String,
  author: ObjectId,
  writeDate: Date = new Date,
  var wasRead: Boolean = false)

case class Box(id: ObjectId = new ObjectId, owner: ObjectId, var messages: List[PrivateMessage] = Nil)
trait TBox {
  val collection: MongoCollection
  val mongo = MongoDBObject

  implicit def pmToMongo(pm: PrivateMessage) = {
    mongo(
      "_id" -> pm.id,
      "title" -> pm.title,
      "content" -> pm.content,
      "author" -> pm.author,
      "writeDate" -> pm.writeDate,
      "wasRead" -> pm.wasRead)
  }

  def pmFromMongo(m: DBObject) = {
    PrivateMessage(
      m.as[ObjectId]("_id"),
      m.as[String]("title"),
      m.as[String]("content"),
      m.as[ObjectId]("author"),
      m.as[Date]("writeDate"),
      m.as[Boolean]("wasRead"))
  }

  implicit def boxToMongo(box: Box) = {
    mongo("_id" -> box.id, "owner" -> box.owner, "messages" -> box.messages.map(pmToMongo))
  }

  def boxFromMongo(m: DBObject) = {
    Box(
      m.as[ObjectId]("_id"),
      m.as[ObjectId]("owner"),
      m.as[BasicDBList]("messages").map {
        message =>
          pmFromMongo(message.asInstanceOf[DBObject])
      }.toList)
  }

  def create(box: Box) = collection.save(box)
  def delete(box: Box) = collection.remove(box)

  def findOneByOwmerId(owner: ObjectId) = {
    collection.findOne(mongo("owner" -> owner)).map(boxFromMongo)
  }

  def update(box: Box) = collection.save(box)
}

object InBox extends TBox {
  override val collection = MongoConnection()("csn")("inboxes")
}

object OutBox extends TBox {
  override val collection = MongoConnection()("csn")("outboxes")
}