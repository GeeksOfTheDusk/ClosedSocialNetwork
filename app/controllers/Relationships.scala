package controllers
import play.api.mvc._
import jp.t2v.lab.play20.auth._
import com.twitter.json.Json
import models._
import play.api.i18n.Messages

object Relationships extends Controller with Auth with AuthImpl  {
  import views._
  
  def mark(id: Long) = authorizedAction(BasicUser) { cuser => implicit request =>
    val rel = Relationship(from_id = cuser.id, to_id = id)
    val existing = Relationship.findAllFromTo(cuser.id, id)
    val user = User.findBy("id" -> id.toString).head
    val userLink = """<a href="/users/"""+user.username+"\">"+user.username+"</a>"
    if(existing.isEmpty) {
      Relationship.create(rel)
      Redirect(routes.Private.index()).flashing("info" -> Messages("following", userLink))
    } else {
      Redirect(routes.Private.index()).flashing("warning" -> Messages("already_following", userLink))
    }
  }

  def unmark(id: Long) = authorizedAction(BasicUser) { cuser => implicit request =>
    val rel = Relationship.findAllFromTo(cuser.id, id)
    val user = User.findBy("id" -> id.toString).head
    val userLink = """<a href="/users/"""+user.username+"\">"+user.username+"</a>"
    if(rel.isEmpty) {
      Redirect(routes.Private.index()).flashing("info" -> Messages("not_following", userLink))
    } else {
      Relationship.delete(rel.head.id)
      Redirect(routes.Private.index()).flashing("warning" -> Messages("no_longer_following", userLink))
    }
  }

  def showMarkingUsers = authorizedAction(BasicUser) { user => implicit request =>
    val marked = user.getAllMarking map { id =>
      User.findBy("id" -> id.toString).head
    }
    Ok(views.html.Private.showMarkedUsers(user, marked))
  }

  def showMarkedUsers = authorizedAction(BasicUser) { user => implicit request =>
    val marked = user.getAllMarked map { id =>
      User.findBy("id" -> id.toString).head
    }
    Ok(views.html.Private.showMarkedUsers(user, marked))
  }

  def showMarkedUsersAsJSON = authorizedAction(BasicUser) { user => implicit request =>
    val markedUsers = user.getAllMarked map { id =>
      User.findBy("id" -> id.toString).head
    }
    
    val markingUser = user.getAllMarking map  { id =>
      User.findBy("id" -> id.toString).head
    }
    
    Ok(Json.build(Map("following" -> markedUsers, "followedBy" -> markingUser)).toString).as("application/json")
  }
}