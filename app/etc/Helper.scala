import play.api.Configuration

package object etc {
  val config = Configuration.load(null)
  def fromConfig(key: String) = config.getString(key).get
}

