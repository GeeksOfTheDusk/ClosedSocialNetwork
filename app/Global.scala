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
    if(User.count == 0) {
      User.create(User(
        0, "frogurth", Crypto.sign("mysecret"), "m", "1988-01-25", "blabla blabla",
        false, 0, new Date, new Date, true
      ))

      User.create(User(
        0, "god", Crypto.sign("bible"), "q", "2000-01-01", "blabla blabla",
        false, 0, new Date, new Date, false
      ))

      User.create(User(
        0, "geek", Crypto.sign("ente"), "m", "2000-01-01", "blabla blabla",
        false, 0, new Date, new Date, true
      ))

      PrivateMessage.create(PrivateMessage(title = Some("test"), authorID = 2, receiverID = 1, content = "hello"))
      Blogentry.create(Blogentry(creatorID = 1, creatorST = "fogurth", title = "Testentry",
        content = "Hello, this is a testentry.", commentsAllowed = true, privacy = 0))

      Blogentry.create(Blogentry(creatorID = 1, creatorST = "fogurth", title = "Testentry 2",
        content = "Hello, this is the second testentry.", commentsAllowed = false, privacy = 0))

      Blogentry.create(Blogentry(creatorID = 3, creatorST = "geek", title = "Testentry",
        content = "Hello, this is a testentry.", commentsAllowed = true, privacy = 0))
    }

    controllers.BlogEngine.commentFormToHtml = { (f, e, r) =>
      views.html.Private.showBlogEntry(f,e)(r.flash, r.session)
    }

    controllers.BlogEngine.entryFormToHtml = { (f, r) =>
      views.html.Private.newBlogEntry(f)(r.flash, r.session)
    }
  }
}
