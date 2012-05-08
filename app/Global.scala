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
    }
  }
}
