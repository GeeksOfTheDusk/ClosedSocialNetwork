import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "CSN"
    val appVersion      = "0.3.0"

    val appDependencies = Seq(
      "mysql" % "mysql-connector-java" % "5.1.20",
      "csn_bbcodeparser" % "csn_bbcodeparser_2.9.1" % "1.0-SNAPSHOT",
      "twitter_bootstrap_module" % "twitter_bootstrap_module_2.9.1" % "1.0-SNAPSHOT",
      "csn_mini_blogengine" % "csn_mini_blogengine_2.9.1" % "0.3.0-SNAPSHOT",
      "jp.t2v" %% "play20.auth" % "0.3-SNAPSHOT"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      resolvers += "CSN Modules Repository" at "http://repositories.coding-minds.com/modules/releases/",
      resolvers += "t2v.jp repo" at "http://www.t2v.jp/maven-repo/"
    )
}
