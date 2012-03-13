package controllers

import play.api.mvc._
import models._
import java.util.Date

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
  
  def showPm(id: Long) = Authenticated { implicit request =>
    val messages = PrivateMessage.allReceived(request.user.id).filter(_.id == id )
    val m = messages.head
    m.readAt = Some(new Date)
    PrivateMessage.update(m)
    Ok(html.Private.showMessage(m))
  }
  
  def me = Authenticated { implicit request =>
    val keys = models.InvitationKey.findByCreator(request.user.id)
    Ok(html.Private.showProfile(request.user, keys))
  }
  
  def newMessage(name: String) = Authenticated { implicit request =>
    val users = User.findBy("username" -> name)
    if(users.isEmpty)
      Redirect(routes.Private.index()).flashing("notFound" -> ("User " + name + " not found"))
    Ok(html.Private.messageForm(Forms.messageForm, users.head.id))
  }
  
  def writeMessage(to: Long) = Authenticated { implicit request =>
    Forms.messageForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.Private.messageForm(formWithErrors, to)),
      value =>  { val (title, content) = value
        val optionTitle = if(title.isEmpty) None else Some(title)
        PrivateMessage.create(PrivateMessage(authorID = request.user.id,
          receiverID = to,  title = optionTitle, content = content))
        Redirect(routes.Private.index()).flashing("message" -> "Message send.")
      }
    )
  }

  def createKey = Authenticated { implicit request =>
    models.InvitationKey.create(InvitationKey(creator_id = request.user.id))
    Redirect(routes.Private.me()).flashing("key" -> "New invitation key generated.")
  }

  def reply(id: Long) = Authenticated { implicit request =>
    val messages = PrivateMessage.findById(id)
    if(messages.isEmpty) {
      Redirect(routes.Private.listPm()).flashing("nopm" -> "Message not found")
    } else {
      val form = Forms.messageForm.fill(("Re: " + messages.head.title.getOrElse(""), "----\n" + messages.head.content + "\n----"))
      Ok(html.Private.messageForm(form, messages.head.authorID))
    }
  }

  def editUser = Authenticated { implicit request =>
    val data = ((request.user.username, "", ""), request.user.dateOfBirth, request.user.dateOfDeath,
      request.user.description, request.user.anonym, request.user.isAdmin)
    val filledForm = Forms.editUserForm.fill(data)
    Ok(html.Private.editUserForm(filledForm, request.user))
  }
  
  def saveUser = Authenticated { implicit request =>
    Forms.editUserForm.bindFromRequest.fold(
      errors =>{println("failed"); BadRequest(html.Private.editUserForm(errors, request.user))},
      value => {
        println("success")
        val((_,pw,_),bday, dday, about, anonym, _) = value
        val user = request.user
        user.hashedPW = if(!pw.isEmpty)pw else user.hashedPW
        user.dateOfBirth = bday
        user.dateOfDeath = dday
        user.description = about
        user.anonym = anonym
        User.update(user)
        Redirect(routes.Private.me())
      }
    )
  }
}




