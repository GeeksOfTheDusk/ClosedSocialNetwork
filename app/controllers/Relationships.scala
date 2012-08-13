package controllers
import play.api.mvc._
import jp.t2v.lab.play20.auth._
import com.twitter.json.Json
import models._
import play.api.i18n.Messages

object Relationships extends Controller with Auth with AuthImpl  {
  import views._
  
  def mark(targetName: String) = authorizedAction(BasicUser) { user => implicit request =>
    val toFollow = User.findOneByName(targetName).get
    val userLink = """<a href="/users/"""+toFollow.username+"\">"+toFollow.username+"</a>"
    if(!user.following.contains(toFollow.id)) {
      user.follow(toFollow)
      Redirect(routes.Private.index()).flashing("info" -> Messages("following", userLink))
    } else
      Redirect(routes.Private.index()).flashing("warning" -> Messages("already_following", userLink))
  }

  def unmark(targetName: String) = authorizedAction(BasicUser) { user => implicit request =>
    val target = User.findOneByName(targetName).get
    val userLink = """<a href="/users/"""+target.username+"\">"+target.username+"</a>"
    if(user.following.contains(target.id)) {
      user.following = user.following.remove(_.toString == target.id.toString)
      Redirect(routes.Private.index()).flashing("warning" -> Messages("no_longer_following", userLink))
    } else {
      Redirect(routes.Private.index()).flashing("info" -> Messages("not_following", userLink))
    }
  }

  def showMarkingUsers = authorizedAction(BasicUser) { user => implicit request =>
    val marked = user.following map { id =>
      User.findOneById(id).get
    }
    Ok(views.html.Private.showMarkedUsers(user, marked))
  }

  def showMarkedUsers = authorizedAction(BasicUser) { user => implicit request =>
    val marked = user.followed
    Ok(views.html.Private.showMarkedUsers(user, marked))
  }

  def showMarkedUsersAsJSON = authorizedAction(BasicUser) { user => implicit request =>
    val markedUsers = user.followed
    
    val markingUser = user.following map  { id =>
      User.findOneById(id).get
    }
    
    Ok(Json.build(Map("following" -> markedUsers, "followedBy" -> markingUser)).toString).as("application/json")
  }
}