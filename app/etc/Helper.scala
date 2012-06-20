import java.text.SimpleDateFormat
import java.util.Date
import models.User
import play.api.Configuration
import views.html.helper.FieldConstructor
import scala.util.matching._
import play.api.cache.Cache

package object etc {
  val config = Configuration.load(null)
  def fromConfig(key: String) = config.getString(key).get
  
  def resolveCache(sessionId: Option[String]) = {
    import play.api.Play.current
    (for {id <- sessionId 
    	 c <- Cache.get(id+":sessionId")}
    yield true).getOrElse(false)
  }

  implicit def dateToString(date: Date) = new {
    def normalize = {
      val sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss")
      sdf.format(date)
    }
  }
  
  implicit def listToHeadOption(list: List[User]) = new {
    def ^? = {
      if(list.isEmpty)
        None
      else
        Option(list.head)
    }
  }
  
  implicit def longUserExists(id: Long) = new {
    def ? = {
      if(User.findBy("id" -> id.toString).isEmpty) {
        false
      } else {
        true
      }
    }
  }
}

