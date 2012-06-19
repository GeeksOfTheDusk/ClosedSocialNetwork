package controllers
import jp.t2v.lab.play20.auth.AuthConfig
import etc._
import play.api.mvc._
import play.api.mvc.Results._
import jp.t2v.lab.play20.auth.LoginLogout
import scala.annotation.tailrec
import scala.util.Random
import java.security.SecureRandom

trait AuthImpl extends AuthConfig {
  type Id = Long
  type User = models.User
  type Authority = User => Boolean
  
  val idManifest: ClassManifest[Id] = classManifest[Id]
  
  val sessionTimeoutInSeconds: Int = 3600
  
  def resolveUser(id: Id): Option[User] =  models.User.findBy("id" -> id.toString()).^?
  
  def loginSucceeded[A](request: Request[A]): PlainResult = Redirect(routes.Private.index)
  
  def logoutSucceeded[A](request: Request[A]): PlainResult = Redirect(routes.Application.index)
 
  def authenticationFailed[A](request: Request[A]): PlainResult = Redirect(routes.Application.login)
  
  def authorizationFailed[A](request: Request[A]): PlainResult = Forbidden("no permission")
  
  def authorize(user: User, authority: Authority) = authority(user)
  
  def BasicUser(user: User) = true
  
  def Admin(user: User) = user.isAdmin
}

trait LoginLogoutNeo extends LoginLogout {
  self: Controller with AuthConfig =>
    
  def gotoLoginSucceeded[A](userId: Id, username: String, userSt: String)(implicit request: Request[A]): PlainResult = {
    resolver.removeByUserId(userId)
    val sessionId = generateSessionId(request)
    val session = resolver.store(sessionId, userId, sessionTimeoutInSeconds)
    loginSucceeded(request).withSession(session + ("sessionId" -> sessionId) + 
        ("id" -> userId.toString) + 
        ("user" -> username) + 
        ("userST" -> userSt))
  }
  
  @tailrec
  private def generateSessionId[A](implicit request: Request[A]): String = {
    val table = "abcdefghijklmnopqrstuvwxyz1234567890-_.!~*'()"
    val token = Stream.continually(random.nextInt(table.size)).map(table).take(64).mkString
    if (resolver.exists(token)) generateSessionId(request) else token
  }

  private val random = new Random(new SecureRandom())
}
