package models

import com.mongodb.casbah.Imports._
import java.util.Date
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.commons.MongoDBObjectBuilder
import org.bson.types.ObjectId

case class User(id: ObjectId = new ObjectId,
  username: String,
  var hashedPassword: String,
  var gender: String = "m",
  var dateOfBirth: Date = new Date,
  var description: String = "NA",
  var invitationKeys: List[String] = Nil,
  var following: List[ObjectId] = Nil,
  var anonym: Boolean = false,
  invitedBy: String = "None",
  registrationDate: Date = new Date,
  var lastLogin: Date = new Date,
  var isAdmin: Boolean = false) {

  def followed = {
    User.findAllBy("following" -> id)
  }

  def follow(user: User) = {
    following = user.id :: following
    User.update(this)
  }
  
  def isFollowing(userId: ObjectId) = {
    following.exists(_.toString == userId.toString)
  }
  
  def inBox = InBox.findOneByOwmerId(id)
  def outBox = OutBox.findOneByOwmerId(id)
  
  def send(message: PrivateMessage) = {
    val box = outBox.get
    box.messages = message :: box.messages
    OutBox.update(box)
  }
  
  def receive(message: PrivateMessage) = {
    val box = inBox.get
    box.messages = message :: box.messages
	InBox.update(box)
  }
}

object User {
  val adminID = new ObjectId("Admin")
  val mongo = MongoDBObject

  implicit def userToMongo(user: User) = {
    mongo(
      "_id" -> user.id,
      "username" -> user.username,
      "hashedPW" -> user.hashedPassword,
      "gender" -> user.gender,
      "dateOfBirth" -> user.dateOfBirth,
      "desc" -> user.description,
      "invitationKeys" -> user.invitationKeys,
      "following" -> user.following,
      "anonym" -> user.anonym,
      "invitedBy" -> user.invitedBy,
      "registrationDate" -> user.registrationDate,
      "lastLogin" -> user.lastLogin,
      "isAdmin" -> user.isAdmin)
  }

  implicit def mongoToUser(m: DBObject): User = {
    User(
      m.as[ObjectId]("_id"),
      m.as[String]("username"),
      m.as[String]("hashedPW"),
      m.as[String]("gender"),
      m.as[Date]("dateOfBirth"),
      m.as[String]("desc"),
      m.as[BasicDBList]("invitationKeys").map(_.asInstanceOf[String]).toList,
      m.as[BasicDBList]("following").map(_.asInstanceOf[ObjectId]).toList,
      m.as[Boolean]("anonym"),
      m.as[String]("invitedBy"),
      m.as[Date]("registrationDate"),
      m.as[Date]("lastLogin"),
      m.as[Boolean]("isAdmin"))
  }

  val collection = MongoConnection()("csn")("users")

  def create(user: User) = {
    collection += user
  }

  def delete(user: User) = {
    collection.remove(user)
  }
  
  def deleteBy[A <: String, B](args: (A, B)*) {
    val builder = new MongoDBObjectBuilder
    
    args foreach (builder += _)
    
    collection.remove(builder.result)
  }

  def findOneByName(username: String): Option[User] = {
    val res = collection.findOne(mongo("username" -> username))
    res.map(mongoToUser)
  }

  def findOneById(id: ObjectId): Option[User] = {
    val res = collection.findOne(mongo("_id" -> id))
    res.map(mongoToUser)
  }
  
  def all = {
    collection.find.map(mongoToUser).toList
  }

  def findAllBy[A <: String, B](args: (A, B)*) = {
    val builder = new MongoDBObjectBuilder

    args foreach (builder += _)

    val res = collection.find(builder.result)
    (res map (mongoToUser)).toList
  }
  
  def find(f: DBObject) = {
    val res = collection.find(f)
    (res map (mongoToUser)).toList
  }

  def update(user: User) = {
    val u = collection.findOne(mongo("_id" -> user.id)).get
    val nu = userToMongo(user)
    nu += "_id" -> u("_id")
    collection.save(nu)
  }
  
  def connect(username: String, password: String) = {
    collection.findOne(mongo("username" -> username, "hashedPW" -> password))
  }
}