package controllers

import play.api.mvc._
import java.text.SimpleDateFormat
import models.{InvitationKey, User}
import play.api.libs.Crypto
import play.api.i18n.Messages

object Application extends Controller  {

  import views._

  implicit def stringToDate(s: String) = {
    val df = new SimpleDateFormat("yyyy-MM-dd");
    df.parse(s);
  }

  def index = Action { implicit request =>
    println(request.acceptLanguages.map(_.code).mkString(", "))
    Ok(html.Application.index(request.flash, request.session))
  }

  def users = Action { implicit request =>
    val users: List[models.User] = request.session.get("user").map( u => User.all.filter(_.username!=u))
      .getOrElse(User.all)
    Ok(html.Application.users(users))
  }
  
  def user(name: String) = Action { implicit request =>
    val user = User.findBy("username" -> name)
    Ok(html.Application.showUser(user.head))
  }

  def signup =  Action { implicit request =>
    Ok(html.Application.signup(Forms.signUpForm))
  }
  
  def newUser = Action { implicit request =>
    Forms.signUpForm.bindFromRequest.fold(
      errors => BadRequest(html.Application.signup(errors)),
      value => { val (username, (password, _), key) = value
        val regKey = models.InvitationKey.findByKey(key).head
        User.create(User(
          username = username,
          hashedPW = Crypto.sign(password),
          invitedBy = regKey.creator_id))
        InvitationKey.delete(regKey.id)
        Redirect(routes.Private.index()).withSession("user" -> username).flashing("register" -> Messages("registration_successful"))
      }
    )
  }
  
  def login = Action { implicit request =>
    Ok(html.Application.login(Forms.loginForm))
  }

  def auth = Action { implicit request =>
    Forms.loginForm.bindFromRequest.fold (
      formWithErrors => BadRequest(html.Application.login(formWithErrors)),
      value => { val (username, _) = value
        Redirect(routes.Private.index()).withSession("user" -> username).flashing("login" -> Messages("login_successful"))
      }
    )
  }
}
