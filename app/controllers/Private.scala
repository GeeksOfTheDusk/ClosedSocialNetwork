package controllers

import play.api.mvc._
import models._
import java.util.Date
import play.api.libs.Crypto
import etc._
import play.api.i18n.Messages
import com.twitter.json.Json

object Private extends Controller with Secure {

  import views._

  def index = Authenticated { implicit request =>
    val messages = PrivateMessage.allReceived(request.user.id)
    val c = messages.filter(_.readAt == None).length
    Ok(html.Private.index(c))
  }

  def logout = Authenticated { implicit request =>
    Redirect(routes.Application.index()).withNewSession
  }

  def listPm = Authenticated { implicit request =>
    val messages = PrivateMessage.allReceived(request.user.id)
    Ok(html.Private.listMessages(messages))
  }

  def listPmAsJSON(count: Int) = Authenticated { implicit request =>
    val messages = PrivateMessage.allReceived(request.user.id).sortWith(_.writtenAt.getTime > _.writtenAt.getTime)
      .slice(0, count)
    
    Ok(Json.build(Map("messages" -> messages, "from" -> Messages("from"))).toString).as("application/json")
  }
  
  def showPm(id: Long) = Authenticated { implicit request =>
    val messages = PrivateMessage.allReceived(request.user.id).filter(_.id == id )
    if(messages.isEmpty) {
      Redirect(routes.Private.listPm()).flashing("error" -> Messages("message_not_found"))
    } else {
      val m = messages.head
      m.readAt = Some(new Date)
      PrivateMessage.update(m)
      Ok(html.Private.showMessage(m))
    }
  }
  
  def me = Authenticated { implicit request =>
    val keys = models.InvitationKey.findByCreator(request.user.id)
    Ok(html.Private.showProfile(request.user, keys))
  }
  
  def newMessage(name: String) = Authenticated { implicit request =>
    val users = User.findBy("username" -> name)
    if(users.isEmpty)
      Redirect(routes.Private.index()).flashing("error" -> Messages("user_x_not_found", name))
    Ok(html.Private.messageForm(Forms.messageForm, users.head.id))
  }

  def newMessageEx = Authenticated { implicit request =>
    Ok(html.Private.messageFormEx(Forms.messageFormEx))
  }
  
  def writeMessage(to: Long) = Authenticated { implicit request =>
    if(to == 0) {
      Forms.messageFormEx.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(html.Private.messageFormEx(formWithErrors))
        },
        value =>  { val (username, title, content) = value
          val to = User.findBy("username" -> username).head.id
          val optionTitle = if(title.isEmpty) Some("No Title") else Some(title)
          PrivateMessage.create(PrivateMessage(authorID = request.user.id,
            receiverID = to,  title = optionTitle, content = content))
          Redirect(routes.Private.index()).flashing("info" -> Messages("message_send"))
        }
      )
    } else {
      Forms.messageForm.bindFromRequest.fold(
        formWithErrors => BadRequest(html.Private.messageForm(formWithErrors, to)),
        value =>  { val (title, content) = value
          val optionTitle = if(title.isEmpty) Some("No Title") else Some(title)
          PrivateMessage.create(PrivateMessage(authorID = request.user.id,
            receiverID = to,  title = optionTitle, content = content))
          Redirect(routes.Private.index()).flashing("info" -> Messages("message_send"))
        }
      )
    }
  }

  def createKey = Authenticated { implicit request =>
    models.InvitationKey.create(InvitationKey(creator_id = request.user.id))
    Redirect(routes.Private.me()).flashing("info" -> Messages("new_key"))
  }

  def reply(id: Long) = Authenticated { implicit request =>
    val messages = PrivateMessage.findById(id)
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

  def editUser = Authenticated { implicit request =>
    val data = ((request.user.username, "", ""), request.user.sex, request.user.dateOfBirth,
      request.user.description, request.user.anonym, request.user.isAdmin)
    val filledForm = Forms.editUserForm.fill(data)
    Ok(html.Private.editUserForm(filledForm, request.user))
  }
  
  def saveUser = Authenticated { implicit request =>
    Forms.editUserForm.bindFromRequest.fold(
      errors =>BadRequest(html.Private.editUserForm(errors, request.user)),
      value => {
        val((_,pw,_), gender, bday, about, anonym, _) = value
        val user = request.user
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
  
  def mark(id: Long) = Authenticated { implicit request =>
    val rel = Relationship(from_id = request.user.id, to_id = id)
    val existing = Relationship.findAllFromTo(request.user.id, id)
    val user = User.findBy("id" -> id.toString).head
    val userLink = """<a href="/users/"""+user.username+"\">"+user.username+"</a>"
    if(existing.isEmpty) {
      Relationship.create(rel)
      Redirect(routes.Private.index()).flashing("info" -> Messages("following", userLink))
    } else {
      Redirect(routes.Private.index()).flashing("warning" -> Messages("already_following", userLink))
    }
  }

  def unmark(id: Long) = Authenticated { implicit request =>
    val rel = Relationship.findAllFromTo(request.user.id, id)
    val user = User.findBy("id" -> id.toString).head
    val userLink = """<a href="/users/"""+user.username+"\">"+user.username+"</a>"
    if(rel.isEmpty) {
      Redirect(routes.Private.index()).flashing("info" -> Messages("not_following", userLink))
    } else {
      Relationship.delete(rel.head.id)
      Redirect(routes.Private.index()).flashing("warning" -> Messages("no_longer_following", userLink))
    }
  }

  def showMarkingUsers = Authenticated { implicit request =>
    val marked = request.user.getAllMarking map { id =>
      User.findBy("id" -> id.toString).head
    }
    Ok(views.html.Private.showMarkedUsers(request.user, marked))
  }

  def showMarkedUsers = Authenticated { implicit request =>
    val marked = request.user.getAllMarked map { id =>
      User.findBy("id" -> id.toString).head
    }
    Ok(views.html.Private.showMarkedUsers(request.user, marked))
  }

  def showMarkedUsersAsJSON = Authenticated { implicit request =>
    val markedUsers = request.user.getAllMarked map { id =>
      User.findBy("id" -> id.toString).head
    }
    
    val markingUser = request.user.getAllMarking map  { id =>
      User.findBy("id" -> id.toString).head
    }
    
    Ok(Json.build(Map("following" -> markedUsers, "followedBy" -> markingUser)).toString).as("application/json")
  }

  def deleteProfile = Authenticated { implicit request =>
    User.delete(request.user.id)
    Redirect(routes.Application.index()).withNewSession
  }

  def deleteMessage(id: Long) = Authenticated { implicit request =>
    PrivateMessage.delete(id)
    Redirect(routes.Private.listPm()).flashing("success" -> Messages("pm_deleted"))
  }
}




