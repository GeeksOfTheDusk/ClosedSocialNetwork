
package views.html.Application

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object signup extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[scala.Tuple3[String, scala.Tuple2[String, String], String]],Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(signUpForm: Form[(String, (String, String), String)])(implicit flash: Flash):play.api.templates.Html = {
        _display_ {import helper._


Seq(format.raw/*1.79*/("""
"""),format.raw/*3.1*/("""
"""),_display_(Seq(/*4.2*/main()/*4.8*/ {_display_(Seq(format.raw/*4.10*/("""
    """),_display_(Seq(/*5.6*/form(routes.Application.newUser)/*5.38*/ {_display_(Seq(format.raw/*5.40*/("""
        """),_display_(Seq(/*6.10*/inputText(
            signUpForm("username"),
            '_label -> "Username: ",
            '_help -> "Choose a valid username."
        ))),format.raw/*10.10*/("""

        """),_display_(Seq(/*12.10*/inputPassword(
            signUpForm("password.main"),
            '_label -> "Password: "
        ))),format.raw/*15.10*/("""

        """),_display_(Seq(/*17.10*/inputPassword(
            signUpForm("password.confirm"),
            '_label -> "Repeat password",
            '_help -> "Please repeat your password again.",
            '_error -> signUpForm.error("password")
        ))),format.raw/*22.10*/("""

        """),_display_(Seq(/*24.10*/inputText(
            signUpForm("registrationkey"),
            '_label -> "Registration key: ",
            '_error -> signUpForm.error("registrationkey")
        ))),format.raw/*28.10*/("""
        <input type="submit" value="Login"/>
    """)))})),format.raw/*30.6*/("""
""")))})))}
    }
    
    def render(signUpForm:Form[scala.Tuple3[String, scala.Tuple2[String, String], String]],flash:Flash) = apply(signUpForm)(flash)
    
    def f:((Form[scala.Tuple3[String, scala.Tuple2[String, String], String]]) => (Flash) => play.api.templates.Html) = (signUpForm) => (flash) => apply(signUpForm)(flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Feb 27 18:41:04 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Application/signup.scala.html
                    HASH: 0eea2a326d0eb63768e4f1a50a1758bec150f6f5
                    MATRIX: 582->1|747->78|774->96|805->98|818->104|852->106|887->112|927->144|961->146|1001->156|1165->298|1207->309|1330->410|1372->421|1616->643|1658->654|1847->821|1929->872
                    LINES: 19->1|23->1|24->3|25->4|25->4|25->4|26->5|26->5|26->5|27->6|31->10|33->12|36->15|38->17|43->22|45->24|49->28|51->30
                    -- GENERATED --
                */
            