package models

import java.util.Date
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import com.twitter.json.{Json, JsonSerializable}

case class User(var id: Long = 0,
                var username: String,
                var hashedPW: String,
                var sex: String = "n",
                var dateOfBirth: Date = new Date,
                var description: String = "N/A",
                var anonym: Boolean = false,
                var invitedBy: Long = 0,
                var registrationDate: Date = new Date,
                var lastLogin: Date = new Date,
                var isAdmin: Boolean = false) extends JsonSerializable {
  def toJson() = {
    Json.build(Map(
      "id" -> id,
      "username" -> username,
      "gender" -> sex,
      "birthDate" -> dateOfBirth,
      "about" -> description,
      "invitedBy" -> invitedBy
    )).toString
  }
  
  def getAllMarked = {
    Relationship.findAllFrom(id) map {_.to_id}
  }

  def getAllMarking = {
    Relationship.findAllTo(id) map {_.from_id}
  }

  def isMarking(id: Long) = getAllMarked.contains(id)
}

object User {
  def user = {
    get[Long]("id") ~ get[String]("username") ~ get[String]("hashedPW") ~ get[String]("sex") ~ get[Date]("dateOfBirth") ~
      get[String]("description") ~ get[Boolean]("anonym") ~ get[Long]("invitedBy") ~
      get[Date]("registrationDate") ~ get[Date]("lastLogin") ~ get[Boolean]("isAdmin") map {
      case id~username~hashedPW~sex~dateOfBirth~description~anonym~invitedBy~registrationDate~lastLogin~isAdmin =>
        User(id, username, hashedPW, sex, dateOfBirth,
          description, anonym, invitedBy,
          registrationDate, lastLogin, isAdmin)
    }
  }

  def all = DB.withConnection { implicit c =>
    SQL("select * from User").as(user *)
  }

  def notNotAnonym = DB.withConnection { implicit c =>
    SQL("select * from User where anonym is false").as(user *)
  }

  def findBy(param: (String,  String)):List[User] =  DB.withConnection { implicit c =>
    SQL("select * from User where " + param._1 + " = {p}").on('p -> param._2).as(user *)
  }

  def count: Long = DB.withConnection { implicit c =>
    SQL("select count(*) from User").as(scalar[Long].single)
  }

  def create(user: User) {
    DB.withConnection { implicit c =>
      SQL(
        """
        insert into User
        (username, hashedPW, sex, dateOfBirth, description, anonym, invitedBy, registrationDate, lastLogin, isadmin)
        values ( {name}, {pw}, {sex}, {bday}, {desc}, {an}, {by}, {reg}, {ll}, {admin} );
        """)
        .on('name -> user.username , 'pw -> user.hashedPW, 'sex -> user.sex, 'bday -> user.dateOfBirth
        , 'desc -> user.description, 'an -> user.anonym, 'by -> user.invitedBy, 'reg -> user.registrationDate
        , 'll -> user.lastLogin, 'admin -> user.isAdmin)
        .executeUpdate()
    }
  }

  def delete(id: Long) {
    PrivateMessage.allReceived(id).foreach { m =>
      PrivateMessage.delete(m.id)
    }
    
    for(f <- Relationship.findAllTo(id)) {
      Relationship.delete(f.id)
    }

    for(f <- Relationship.findAllFrom(id)) {
      Relationship.delete(f.id)
    }
    
    DB.withConnection { implicit c =>
      SQL("delete from User where id = {id}")
        .on('id -> id)
        .executeUpdate()
    }
  }

  def update(user: User) {
    DB.withConnection { implicit c =>
      SQL(
        """
        update User
        set username={name},
        hashedPW={pw},
        sex={sex},
        dateOfBirth={bday},
        description={desc},
        anonym={an},
        invitedBy={by},
        registrationDate={reg},
        lastLogin={ll},
        isadmin={admin}
        where id = {id}
        """)
        .on('name -> user.username , 'pw -> user.hashedPW, 'sex -> user.sex, 'bday -> user.dateOfBirth
        , 'desc -> user.description, 'an -> user.anonym, 'by -> user.invitedBy, 'reg -> user.registrationDate
        , 'll -> user.lastLogin, 'admin -> user.isAdmin, 'id -> user.id)
        .executeUpdate()
    }
  }
  
  def connect(username: String,  password: String) = DB.withConnection { implicit c =>
    SQL("select * from User where username like {username} and hashedPW = {hash}").on(
    'username -> username,
    'hash -> password
    ).as(user *)
  }
}
