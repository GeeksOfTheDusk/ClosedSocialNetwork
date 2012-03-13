package controllers

import models._
import play.api.mvc._

case class AuthenticatedRequest(val user: User, request: Request[AnyContent]) extends WrappedRequest(request)

trait Secure {
  self: Controller =>
  def Authenticated(f: AuthenticatedRequest => Result) = {
    Action { request =>
      request.session.get("user").flatMap(u => Some(User.findBy("username" -> u).head)).map { user =>
        f(AuthenticatedRequest(user, request))
      }.getOrElse(Redirect(routes.Application.index()))
    }
  }

  def Admin(f: AuthenticatedRequest => Result) = {
    Action { request =>
      request.session.get("user").flatMap { u =>
        val user = User.findBy("username" -> u).head 
        if(user == null || !user.isAdmin)
          None
        else
          Some(user)
      }.map{ user: User =>
        f(AuthenticatedRequest(user, request))
      }.getOrElse(Forbidden)
    }
  }
}
