package models
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import util.Random

case class InvitationKey(id: Long = 0, 
                         key_string: String = InvitationKey.generateKey(10),
                         creator_id: Long)

object InvitationKey {
  def generateKey(length: Int = 5) = {
    val chars = Array("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u",
      "v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
      "U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"
    )
    
    val random = new Random(System.currentTimeMillis)
    (for(i <- 0 until length) yield chars(random.nextInt(chars.length))).mkString
  }
  def key = {
    get[Long]("id") ~ get[String]("key_string") ~ get[Long]("creator_id") map  {
      case id~key~creator => InvitationKey(id, key, creator)
    }
  }

  def findByKey(keystring: String) = DB.withConnection { implicit c =>
    SQL("select * from InvitationKey where key_string = {key}").on("key" -> keystring).as(key *)
  }


  def findByCreator(id: Long) = DB.withConnection { implicit c =>
    SQL("select * from InvitationKey where creator_id = {id}").on("id" -> id).as(key *)
  }

  def create(key: InvitationKey)  {
    DB.withConnection { implicit c =>
      SQL("insert into InvitationKey (key_string, creator_id) values ({key}, {creator})")
        .on('key -> key.key_string, 'creator -> key.creator_id).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from InvitationKey where id = {id}").on("id" -> id).executeUpdate()
    }
  }
}