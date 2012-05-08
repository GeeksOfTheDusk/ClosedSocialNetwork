package models
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Relationship(id: Long = 0, from_id: Long,  to_id: Long)

object Relationship {
  def rel = {
    get[Long]("id") ~ get[Long]("from_id") ~ get[Long]("to_id") map  {
      case id~from~to => Relationship(id, from, to)
    }
  }

  def create(rel: Relationship)  {
    DB.withConnection { implicit c =>
      SQL("insert into Relationship (from_id, to_id) values ({from}, {to})")
        .on('from -> rel.from_id, 'to -> rel.to_id).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from Relationship where id = {id}").on("id" -> id).executeUpdate()
    }
  }

  def findAllFrom(id: Long) = {
    DB.withConnection { implicit c =>
      SQL("select * from Relationship where from_id = {from}").on('from -> id).as(rel *)
    }
  }

  def findAllTo(id: Long) = {
    DB.withConnection { implicit c =>
      SQL("select * from Relationship where to_id = {from}").on('from -> id).as(rel *)
    }
  }

  def findAllFromTo(from: Long, to: Long) = {
    DB.withConnection { implicit c =>
      SQL("select * from Relationship where from_id = {from} and to_id = {to} ").on('from -> from, 'to -> to).as(rel *)
    }
  }

  def findById(id: Long) = {
    DB.withConnection { implicit c =>
      SQL("select * from Relationship where id = {id}").on('id -> id).as(rel *)
    }
  }
}
