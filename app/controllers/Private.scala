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

  def index = authorizedAction(BasicUser) { user => implicit request =>
    val messages = PrivateMessage.allReceived(user.id)
    val c = messages.filter(_.readAt == None).length
    Ok(html.Private.index(c))
  }

  def listPm = authorizedAction(BasicUser) { user => implicit request =>
    val messages = PrivateMessage.allReceived(user.id)
    Ok(html.Private.listMessages(messages))
  }

  def listPmAsJSON(count: Int) = authorizedAction(BasicUser) { user => implicit request =>
    val messages = PrivateMessage.allReceived(user.id).sortWith(_.writtenAt.getTime > _.writtenAt.getTime)
      .slice(0, count)
    
    Ok(Json.build(Map("messages" -> messages, "from" -> Messages("from"))).toString).as("application/json")
  }
  
  def showPm(id: Long) = authorizedAction(BasicUser) { user => implicit request =>
    val messages = PrivateMessage.allReceived(user.id).filter(_.id == id )
    if(messages.isEmpty) {
      Redirect(routes.Private.listPm()).flashing("error" -> Messages("message_not_found"))
    } else {
      val m = messages.head
      m.readAt = Some(new Date)
      PrivateMessage.update(m)
      Ok(html.Private.showMessage(m))
    }
  }
  
  def me = authorizedAction(BasicUser) { user => implicit request =>
    val keys = models.InvitationKey.findByCreator(user.id)
    Ok(html.Private.showProfile(user, keys))
  }
  
  def newMessage(name: String) = authorizedAction(BasicUser) { user => implicit request =>
    val users = User.findBy("username" -> name)
    if(users.isEmpty)
      Redirect(routes.Private.index()).flashing("error" -> Messages("user_x_not_found", name))
    Ok(html.Private.messageForm(Forms.messageForm, users.head.id))
  }

  def newMessageEx = authorizedAction(BasicUser) { user => implicit request =>
    Ok(html.Private.messageFormEx(Forms.messageFormEx))
  }
  
  def writeMessage(to: Long) = authorizedAction(BasicUser) { user => implicit request =>
    if(to == 0) {
      Forms.messageFormEx.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(html.Private.messageFormEx(formWithErrors))
        },
        value =>  { val (username, title, content) = value
          val to = User.findBy("username" -> username).head.id
          val optionTitle = if(title.isEmpty) Some("No Title") else Some(title)
          PrivateMessage.create(PrivateMessage(authorID = user.id,
            receiverID = to,  title = optionTitle, content = content))
          Redirect(routes.Private.index()).flashing("info" -> Messages("message_send"))
        }
      )
    } else {
      Forms.messageForm.bindFromRequest.fold(
        formWithErrors => BadRequest(html.Private.messageForm(formWithErrors, to)),
        value =>  { val (title, content) = value
          val optionTitle = if(title.isEmpty) Some("No Title") else Some(title)
          PrivateMessage.create(PrivateMessage(authorID = user.id,
            receiverID = to,  title = optionTitle, content = content))
          Redirect(routes.Private.index()).flashing("info" -> Messages("message_send"))
        }
      )
    }
  }

  def createKey = authorizedAction(BasicUser) { user => implicit request =>
    models.InvitationKey.create(InvitationKey(creator_id = user.id))
    Redirect(routes.Private.me()).flashing("info" -> Messages("new_key"))
  }

  def reply(id: Long) = authorizedAction(BasicUser) { user => implicit request =>
    val messages = PrivateMessage.allReceived(user.id).filter(_.id == id )
    if(messages.isEmpty) {
      Redirect(routes.Private.listPm()).flashing("error" -> Messages("message_not_found"))
    } else {
      val content = messages.head.content.split("\n").map("> "+_).mkString("\n")
      val originalAuthor = User.findBy("id" -> messages.head.authorID.toString).^?.map(_.username).getOrElse("NA")
      val header = "from " + originalAuthor + " on " + messages.head.writtenAt.normalize
      val form = Forms.messageForm.fill(("Re: " + messages.head.title.getOrElse(""), " \n" + header + "\n" + content))
      Ok(html.Private.messageForm(form, messages.head.authorID))
    }
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

  def deleteMessage(id: Long) = authorizedAction(BasicUser) { user => implicit request =>
    PrivateMessage.delete(id)
    Redirect(routes.Private.listPm()).flashing("success" -> Messages("pm_deleted"))
  }
  
  def newEntry = authorizedAction(BasicUser) { user => implicit request =>
    Ok(html.Private.newBlogEntry(BlogForms.entryForm))
  }
}




