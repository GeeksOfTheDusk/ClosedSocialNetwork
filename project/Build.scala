import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "CSN"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "csn_bbcodeparser" % "csn_bbcodeparser_2.9.1" % "1.0-SNAPSHOT"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      resolvers += "CSN Modules Github Repository" at "csnmodules.github.com"
    )

}
