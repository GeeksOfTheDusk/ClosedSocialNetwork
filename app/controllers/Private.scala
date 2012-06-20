package controllers

import play.api.mvc._
import models._
import java.util.Date
import play.api.libs.Crypto
import etc._
import play.api.i18n.Messages
import com.twitter.json.Json
import jp.t2v.lab.play20.auth._

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

  def deleteProfile = authorizedAction(BasicUser) { user => implicit request =>
    User.delete(user.id)
    Redirect(routes.Application.index()).withNewSession
  }
  
  def newEntry = authorizedAction(BasicUser) { user => implicit request =>
    Ok(html.Private.newBlogEntry(BlogForms.entryForm))
  }
}




