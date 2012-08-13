import java.text.SimpleDateFormat
import java.util.Date
import models.User
import play.api.Configuration
import views.html.helper.FieldConstructor
import scala.util.matching._
import play.api.cache.Cache
import scala.util.Random
import org.bson.types.ObjectId

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
  
  implicit def idUserExists(id: ObjectId) = new {
    def ? = {
      if(User.findOneById(id).isEmpty) {
        false
      } else {
        true
      }
    }
  }
  
  def generateKey(length: Int = 5) = {
    val chars = Array("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u",
      "v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
      "U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"
    )
    
    val random = new Random(System.currentTimeMillis)
    (for(i <- 0 until length) yield chars(random.nextInt(chars.length))).mkString
  }
}

