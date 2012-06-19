package controllers

import play.api.mvc._
import models._
import java.util.Date
import play.api.libs.Crypto
import etc._
import play.api.i18n.Messages
import com.twitter.json.Json
import jp.t2v.lab.play20.auth.LoginLogout
import jp.t2v.lab.play20.auth.Auth

object Private extends Controller with Auth with AuthImpl {

  import views._

  def index = optionalUserAction { user => implicit request =>
    if(user == None) {
      Redirect(routes.Application.index())
    } else {
	    val messages = PrivateMessage.allReceived(user.get.id)
	    val c = messages.filter(_.readAt == None).length
	    Ok(html.Private.index(c))
    }
  }
  
  def me = authorizedAction(BasicUser) { user => implicit request =>
    val keys = models.InvitationKey.findByCreator(user.id)
    Ok(html.Private.showProfile(user, keys))
  }

  def createKey = authorizedAction(BasicUser) { user => implicit request =>
    models.InvitationKey.create(InvitationKey(creator_id = user.id))
    Redirect(routes.Private.me()).flashing("info" -> Messages("new_key"))
  }

  def editUser = authorizedAction(BasicUser) { user => implicit request =>
    val data = ((user.username, "", ""), user.sex, user.dateOfBirth,
      user.description, user.anonym, user.isAdmin)
    val filledForm = Forms.editUserForm.fill(data)
    Ok(html.Private.editUserForm(filledForm, user))
  }
  
  def saveUser = authorizedAction(BasicUser) { user => implicit request =>
    Forms.editUserForm.bindFromRequest.fold(
      errors =>BadRequest(html.Private.editUserForm(errors, user)),
      value => {
        val((_,pw,_), gender, bday, about, anonym, _) = value
        user.hashedPW = if(!pw.isEmpty)Crypto.sign(pw) else user.hashedPW
        user.dateOfBirth = bday
        user.description = about
        user.anonym = anonym
        user.sex = gender
        User.update(user)
        Redirect(routes.Private.me())
      }
    )
  }
  
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

  def deleteProfile = authorizedAction(BasicUser) { user => implicit request =>
    User.delete(user.id)
    Redirect(routes.Application.index()).withNewSession
  }
  
  def newEntry = authorizedAction(BasicUser) { user => implicit request =>
    Ok(html.Private.newBlogEntry(BlogForms.entryForm))
  }
}




