package controllers

import play.api.mvc._
import models._
import play.api.libs.Crypto
import jp.t2v.lab.play20.auth.Auth

object Admin extends Controller with Auth with AuthImpl{
  import views._
  
  def index = authorizedAction(Admin) { user => implicit request =>
    val users = User.all
    Ok(html.Admin.index(users))
  }

  def delete(id: Long) = authorizedAction(Admin) { user => implicit request =>
    User.delete(id)
    Redirect(routes.Admin.index())
  }
  
  def newUser = authorizedAction(Admin) { user => implicit request =>
    Ok(html.Admin.newUserForm(Forms.newUserForm))
  }
  
  def createUser = authorizedAction(Admin) { user => implicit request =>
    Forms.newUserForm.bindFromRequest.fold (
      formsWithErrors => BadRequest(html.Admin.newUserForm(formsWithErrors)),
      value => {
        val ((username, pw), bday, about, anonym, admin) = value
        val user = new User(username = username, hashedPW = Crypto.sign(pw), dateOfBirth = bday,
          description = about, anonym = anonym, isAdmin = admin)
        User.create(user)
        Redirect(routes.Admin.index())
      }
    )
  }
  
  def editUserForm(id: Long) = authorizedAction(Admin) { user => implicit request =>
    val user = User.findBy("id" -> id.toString).head
    if(user == null) {
      BadRequest(html.Admin.index(User.all))
    } else {
      val toFill = (user.dateOfBirth,
        user.description, user.anonym, user.isAdmin)
      
      Ok(html.Admin.editUserForm(Forms.adminEditUserForm.fill(toFill), user))
    }
  }

  def editUser(id: Long) = authorizedAction(Admin) { user => implicit request =>
    val user = User.findBy("id" -> id.toString).head
    if(user == null) {
      Redirect(routes.Admin.index())
    } else {
      Forms.adminEditUserForm.bindFromRequest.fold (
        formsWithErrors => BadRequest(html.Admin.editUserForm(formsWithErrors, user)),
        value => {
          val (bday, about, anonym, admin) = value
          user.dateOfBirth = bday
          user.description = about
          user.anonym = anonym
          user.isAdmin = admin
          User.update(user)
          Redirect(routes.Admin.index())
        }
      )
    }
  }
  
  def broadcastForm = authorizedAction(Admin) { user => implicit request =>
    Ok(html.Admin.broadcastForm(Forms.messageForm))
  }

  def broadcast = authorizedAction(Admin) { user => implicit request =>
    Forms.messageForm.bindFromRequest.fold (
      errors => BadRequest(html.Admin.broadcastForm(errors)),
      value => { val (title, content) = value
        for(user <- User.all) {
          val pm = new PrivateMessage(title = Some("[Admin] " + title),
                                      content = content,
                                      authorID = 0,
                                      receiverID = user.id)
          PrivateMessage.create(pm)
        }
        Redirect(routes.Admin.index())
      }
    )
  }
}
