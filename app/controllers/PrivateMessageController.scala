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
    val messages = user.inBox.get.messages
    Ok(html.PrivateMessages.listMessages(messages))
  }
  
  def listPmAsJSON(count: Int) = authorizedAction(BasicUser) { user => implicit request =>
    val messages = user.inBox.get.messages.sortWith(_.writeDate.getTime > _.writeDate.getTime)
      .slice(0, count)
    
    Ok(Json.build(Map("messages" -> messages, "from" -> Messages("from"))).toString).as("application/json")
  }
  
  def showPm(id: String) = authorizedAction(BasicUser) { user => implicit request =>
    val messages = user.inBox.get.messages.filter(_.id.toString == id )
    if(messages.isEmpty) {
      Redirect(routes.PrivateMessageController.listPm()).flashing("error" -> Messages("message_not_found"))
    } else {
      val m = messages.head
      m.wasRead = true
      val box = user.inBox.get
      box.updateMessage(m)
      Ok(html.PrivateMessages.showMessage(m))
    }
  }
  
  def newMessage(name: String) = authorizedAction(BasicUser) { user => implicit request =>
    val users = User.findOneByName(name)
    if(users.isEmpty)
      Redirect(routes.Private.index()).flashing("error" -> Messages("user_x_not_found", name))
    Ok(html.PrivateMessages.messageForm(Forms.messageForm, users.get.username))
  }

  def newMessageEx = authorizedAction(BasicUser) { user => implicit request =>
    Ok(html.PrivateMessages.messageFormEx(Forms.messageFormEx))
  }
  
  def writeMessage(to: String) = authorizedAction(BasicUser) { user => implicit request =>
    if(to == "") {
      Forms.messageFormEx.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(html.PrivateMessages.messageFormEx(formWithErrors))
        },
        value =>  { val (username, title, content) = value
          val receiver = User.findOneByName(username).get
          val pm = PrivateMessage(author = user.id,
            title = title, content = content)
            
          receiver receive pm 
          user send pm
          Redirect(routes.Private.index()).flashing("info" -> Messages("message_send"))
        }
      )
    } else {
      Forms.messageForm.bindFromRequest.fold(
        formWithErrors => BadRequest(html.PrivateMessages.messageForm(formWithErrors, to)),
        value =>  { val (title, content) = value
          val receiver = User.findOneByName(to).get
          val pm = PrivateMessage(author = user.id,
            title = title, content = content)
            
          receiver receive pm 
          user send pm
          Redirect(routes.Private.index()).flashing("info" -> Messages("message_send"))
        }
      )
    }
  }
  
  def reply(id: String) = authorizedAction(BasicUser) { user => implicit request =>
    val messages = user.inBox.get.messages.filter(_.author.toString == id )
    if(messages.isEmpty) {
      Redirect(routes.PrivateMessageController.listPm()).flashing("error" -> Messages("message_not_found"))
    } else {
      val content = messages.head.content.split("\n").map("> "+_).mkString("\n")
      val id = messages.head.author
      val originalAuthor = User.findOneById(id)
      val authorName = originalAuthor.map(_.username).getOrElse("NA")
      val header = "from " + authorName + " on " + messages.head.writeDate.normalize
      val form = Forms.messageForm.fill(("Re: " + messages.head.title, " \n" + header + "\n" + content))
      Ok(html.PrivateMessages.messageForm(form, originalAuthor.get.username))
    }
  }
  
  def deleteMessage(id: String) = authorizedAction(BasicUser) { user => implicit request =>
    val inbox = user.inBox.get
    inbox.messages = inbox.messages.remove(_.id.toString == id)
    InBox.update(inbox)
    val outbox = user.outBox.get
    outbox.messages = outbox.messages.remove(_.id.toString == id)
    OutBox.update(outbox)
    Redirect(routes.PrivateMessageController.listPm()).flashing("success" -> Messages("pm_deleted"))
  }
}