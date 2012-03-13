package controllers

import models._
import play.api.mvc._

case class AuthenticatedRequest(val user: User, request: Request[AnyContent]) extends WrappedRequest(request)
