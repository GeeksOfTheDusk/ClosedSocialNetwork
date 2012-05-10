import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "CSN"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "csn_bbcodeparser" % "csn_bbcodeparser_2.9.1" % "1.0-SNAPSHOT",
      "twitter_bootstrap_module" % "twitter_bootstrap_module_2.9.1" % "1.0-SNAPSHOT"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      resolvers += Resolver.url("CSN Modules Repository", url("http://frogurth.github.com/releases/"))(Resolver.ivyStylePatterns)
    )

}
