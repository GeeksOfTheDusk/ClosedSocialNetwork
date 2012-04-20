import java.text.SimpleDateFormat
import java.util.Date
import play.api.Configuration
import views.html.helper.FieldConstructor

package object etc {
  val config = Configuration.load(null)
  def fromConfig(key: String) = config.getString(key).get

  implicit def dateToString(date: Date): String = {
    val sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss")
    sdf.format(date)
  }
  
  object Constructors {
    implicit val twitterConstructor = FieldConstructor(html.twitterFieldConstructor.f)
  }
}

