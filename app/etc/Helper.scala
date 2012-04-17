import play.api.Configuration
import views.html.helper.FieldConstructor

package object etc {
  val config = Configuration.load(null)
  def fromConfig(key: String) = config.getString(key).get

  object Constructors {
    implicit val twitterConstructor = FieldConstructor(html.twitterFieldConstructor.f)
  }
}

