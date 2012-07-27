import java.text.SimpleDateFormat
import java.util.Date
import models._
import play.api._
import libs.Crypto
import mvc.{Handler, RequestHeader}

object Global extends GlobalSettings {
  implicit def stringToDate(s: String) = {
    val df = new SimpleDateFormat("yyyy-MM-dd");
    df.parse(s);
  }


  override def onStart(app: Application) {
    
    controllers.BlogEngine.commentFormToHtml = { (f, e, r) =>
      views.html.Private.showBlogEntry(f,e)(r.flash, r.session)
    }

    controllers.BlogEngine.entryFormToHtml = { (f, r) =>
      views.html.Private.newBlogEntry(f)(r.flash, r.session)
    }
  }
}
