package controllers
import play.api.mvc._
import jp.t2v.lab.play20.auth.Auth
import models._
import com.twitter.json.Json
import play.api.i18n.Messages
import java.util.Date
import etc._

object PrivateMessageController extends Controller with Auth with AuthImpl  {
  import views._
  
  def listPm = authorizedAction(BasicUser) { user => implicit request =>
    val messages = PrivateMessage.allReceived(user.id)
    Ok(html.PrivateMessages.listMessages(messages))
  }
  
  def listPmAsJSON(count: Int) = authorizedAction(BasicUser) { user => implicit request =>
    val messages = PrivateMessage.allReceived(user.id).sortWith(_.writtenAt.getTime > _.writtenAt.getTime)
      .slice(0, count)
    
    Ok(Json.build(Map("messages" -> messages, "from" -> Messages("from"))).toString).as("application/json")
  }
  
  def showPm(id: Long) = authorizedAction(BasicUser) { user => implicit request =>
    val messages = PrivateMessage.allReceived(user.id).filter(_.id == id )
    if(messages.isEmpty) {
      Redirect(routes.PrivateMessageController.listPm()).flashing("error" -> Messages("message_not_found"))
    } else {
      val m = messages.head
      m.readAt = Some(new Date)
      PrivateMessage.update(m)
      Ok(html.PrivateMessages.showMessage(m))
    }
  }
  
  def newMessage(name: String) = authorizedAction(BasicUser) { user => implicit request =>
    val users = User.findBy("username" -> name)
    if(users.isEmpty)
      Redirect(routes.Private.index()).flashing("error" -> Messages("user_x_not_found", name))
    Ok(html.PrivateMessages.messageForm(Forms.messageForm, users.head.id))
  }

  def newMessageEx = authorizedAction(BasicUser) { user => implicit request =>
    Ok(html.PrivateMessages.messageFormEx(Forms.messageFormEx))
  }
  
  def writeMessage(to: Long) = authorizedAction(BasicUser) { user => implicit request =>
    if(to == 0) {
      Forms.messageFormEx.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(html.PrivateMessages.messageFormEx(formWithErrors))
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
        formWithErrors => BadRequest(html.PrivateMessages.messageForm(formWithErrors, to)),
        value =>  { val (title, content) = value
          val optionTitle = if(title.isEmpty) Some("No Title") else Some(title)
          PrivateMessage.create(PrivateMessage(authorID = user.id,
            receiverID = to,  title = optionTitle, content = content))
          Redirect(routes.Private.index()).flashing("info" -> Messages("message_send"))
        }
      )
    }
  }
  
  def reply(id: Long) = authorizedAction(BasicUser) { user => implicit request =>
    val messages = PrivateMessage.allReceived(user.id).filter(_.id == id )
    if(messages.isEmpty) {
      Redirect(routes.PrivateMessageController.listPm()).flashing("error" -> Messages("message_not_found"))
    } else {
      val content = messages.head.content.split("\n").map("> "+_).mkString("\n")
      val originalAuthor = User.findBy("id" -> messages.head.authorID.toString).^?.map(_.username).getOrElse("NA")
      val header = "from " + originalAuthor + " on " + messages.head.writtenAt.normalize
      val form = Forms.messageForm.fill(("Re: " + messages.head.title.getOrElse(""), " \n" + header + "\n" + content))
      Ok(html.PrivateMessages.messageForm(form, messages.head.authorID))
    }
  }
  
  def deleteMessage(id: Long) = authorizedAction(BasicUser) { user => implicit request =>
    PrivateMessage.delete(id)
    Redirect(routes.PrivateMessageController.listPm()).flashing("success" -> Messages("pm_deleted"))
  }
}