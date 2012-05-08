import java.text.SimpleDateFormat
import java.util.Date
import models.User
import play.api.Configuration
import views.html.helper.FieldConstructor

package object etc {
  val config = Configuration.load(null)
  def fromConfig(key: String) = config.getString(key).get

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

  implicit def stringToHtml(s: String) = new {
    def escape = {
      s.replace("\n", "<br/>")
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
  
  object Constructors {
    implicit val twitterConstructor = FieldConstructor(html.twitterFieldConstructor.f)
  }
}

