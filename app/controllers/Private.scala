package controllers

import play.api.mvc._
import models._
import java.util.Date
import play.api.libs.Crypto
import etc._

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
    var json = new StringBuilder
    json.append("{\n    \"messages\":[")
    for(message <- messages) {
      json.append("""
        {
          "id": """ + message.id + """,
          "author": """" + User.findBy("id" -> message.authorID.toString).^?.map(_.username).getOrElse("NA") + """",
          "authorID": """ + (if(message.authorID.?) message.authorID else -1) + """,
          "title": """" + message.title.get + """",
          "writtenAt": """" + message.writtenAt + """",
          "new": """ + {if(message.readAt == None){"true"}else{"false"}} + """
        }""")

      if(message != messages.last) {
        json.append(",")
      }
    }

    json.append("\n    ]\n}")
    
    Ok(json.toString).as("application/json")
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
          val optionTitle = if(title.isEmpty) None else Some(title)
          PrivateMessage.create(PrivateMessage(authorID = request.user.id,
            receiverID = to,  title = optionTitle, content = content))
          Redirect(routes.Private.index()).flashing("message" -> "Message send.")
        }
      )
    } else {
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
      errors =>BadRequest(html.Private.editUserForm(errors, request.user)),
      value => {
        val((_,pw,_),bday, dday, about, anonym, _) = value
        val user = request.user
        user.hashedPW = if(!pw.isEmpty)Crypto.sign(pw) else user.hashedPW
        user.dateOfBirth = bday
        user.dateOfDeath = dday
        user.description = about
        user.anonym = anonym
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
      Redirect(routes.Private.index()).flashing("Follow" -> ("You are now following " + userLink))
    } else {
      Redirect(routes.Private.index()).flashing("Follow" -> ("You are already following " + userLink))
    }
  }

  def unmark(id: Long) = Authenticated { implicit request =>
    val rel = Relationship.findAllFromTo(request.user.id, id)
    val user = User.findBy("id" -> id.toString).head
    val userLink = """<a href="/users/"""+user.username+"\">"+user.username+"</a>"
    if(rel.isEmpty) {
      Redirect(routes.Private.index()).flashing("Follow" -> ("You are not following " + userLink))
    } else {
      Relationship.delete(rel.head.id)
      Redirect(routes.Private.index()).flashing("Follow" -> ("You are no longer following " + userLink))
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

    var json = new StringBuilder
    json.append("{\n    \"marked\":[")
    for(u <- markedUsers) {
      json.append("""
        {
          "id": """ + u.id + """,
          "username": """" + u.username + """"
        }""")

      if(u != markedUsers.last) {
        json.append(",")
      }
    }

    json.append("\n    ]\n    \"markedByOthers\": " + !request.user.getAllMarking.isEmpty + "\n}")

    Ok(json.toString).as("application/json")
  }

  def deleteProfile = Authenticated { implicit request =>
    User.delete(request.user.id)
    Redirect(routes.Application.index()).withNewSession
  }
}




