import java.text.SimpleDateFormat
import java.util.Date
import models.User
import play.api.Configuration
import views.html.helper.FieldConstructor
import scala.util.matching._

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
      var html = s.replace("\n", "<br/>")
        .replace("[b]", "<b>").replace("[/b]", "</b>")
        .replace("[i]", "<i>").replace("[/i]", "</i>")
        .replace("[u]", "<u>").replace("[/u]", "</u>")
        .replace("[center]", "<center>").replace("[/center]", "</center>")

      html = replaceFontWithRegex(html)

      html.replace("\n", "<br/>")
    }
  }

  private def replaceFontWithRegex(s: String) = {
    var re = s
    val fontRegex = new Regex("\\[font=([^\\]]+)\\]", "font")
    re = fontRegex replaceAllIn (re, m => "<font face=\"" + m.group("font") + "\">") replace ("[/font]", "</font>")

    val colorRegex = new Regex("\\[color=([^\\]]+)\\]", "color")
    re = colorRegex replaceAllIn (re, m => "<font color=\"" + m.group("color") + "\">") replace ("[/color]", "</font>")

    val sizeRegex = new Regex("\\[size=([^\\]]+)\\]", "size")
    re = sizeRegex replaceAllIn (re, m => "<font size=\"" + m.group("size") + "\">") replace ("[/size]", "</font>")
    re
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

