package controllers

import play.api.mvc._
import models.User
import play.api.libs.Crypto
import play.api.i18n.Messages
import jp.t2v.lab.play20.auth._
import etc._

object Application extends Controller with Auth with LoginLogoutNeo with AuthImpl {
 
  import views._

  def index = optionalUserAction { maybeUser => implicit request =>
    Ok(html.Application.index(request.flash, request.session))
  }

  def users = optionalUserAction { maybeUser => implicit request =>
    import com.mongodb.casbah.Imports._
    val users: List[models.User] = request.session.get("user").map( u => User.find("username" $ne u))
      .getOrElse(User.find(MongoDBObject()))
    Ok(html.Application.users(users))
  }
  
  def user(name: String) = optionalUserAction { maybeUser => implicit request =>
    val user = User.findOneByName(name)
    Ok(html.Application.showUser(user.get))
  }

  def signup =  optionalUserAction { maybeUser => implicit request =>
    Ok(html.Application.signup(Forms.signUpForm))
  }
  
  def newUser = optionalUserAction { maybeUser => implicit request =>
    Forms.signUpForm.bindFromRequest.fold(
      errors => BadRequest(html.Application.signup(errors)),
      value => { val (username, (password, _), key) = value
        val by = models.User.findAllBy("keys" -> key).head
        User.create(User(
          username = username,
          hashedPassword = Crypto.sign(password),
          invitedBy = by.id.toString))
          
          //TODO Delete key after usage
        val user = User.findOneByName(username)
        request.session + ("user" -> username) + ("id" -> user.get.id.toString) + ("userST" -> username)
        gotoLoginSucceeded(user.get.id.toString, username, username)
          .flashing("success" -> Messages("login_successful"))     
      }
    )
  }
  
  def login = optionalUserAction { maybeUser => implicit request =>
    Ok(html.Application.login(Forms.loginForm))
  }
  
  def logout = optionalUserAction { maybeUser => implicit request =>
    gotoLogoutSucceeded
  }

  def auth = optionalUserAction { maybeUser => implicit request =>
    Forms.loginForm.bindFromRequest.fold (
      formWithErrors => BadRequest(html.Application.login(formWithErrors)),
      value => { val (username, _) = value
        val user = User.findOneByName(username)
		request.session + ("user" -> username) + ("id" -> user.get.id.toString) + ("userST" -> username)
        gotoLoginSucceeded(user.get.id.toString, username, username)
          .flashing("success" -> Messages("login_successful"))
      }
    )
  }
}
