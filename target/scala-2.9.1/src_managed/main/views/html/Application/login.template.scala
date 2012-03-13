
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
object login extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[scala.Tuple2[String, String]],Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(loginForm: Form[(String, String)])(implicit flash: Flash):play.api.templates.Html = {
        _display_ {import helper._


Seq(format.raw/*1.60*/("""
"""),format.raw/*3.1*/("""
"""),_display_(Seq(/*4.2*/main()/*4.8*/ {_display_(Seq(format.raw/*4.10*/("""
    """),_display_(Seq(/*5.6*/form(routes.Application.auth)/*5.35*/ {_display_(Seq(format.raw/*5.37*/("""
        """),_display_(Seq(/*6.10*/inputText(
            loginForm("username"),
            '_label -> "Username: ",
            '_help -> "Choose a valid username"
        ))),format.raw/*10.10*/("""
        """),_display_(Seq(/*11.10*/inputPassword(
            loginForm("password"),
            '_label -> "Password: ",
            '_error -> loginForm.globalError
        ))),format.raw/*15.10*/("""
        <input type="submit" value="Login"/>
    """)))})),format.raw/*17.6*/("""
""")))})))}
    }
    
    def render(loginForm:Form[scala.Tuple2[String, String]],flash:Flash) = apply(loginForm)(flash)
    
    def f:((Form[scala.Tuple2[String, String]]) => (Flash) => play.api.templates.Html) = (loginForm) => (flash) => apply(loginForm)(flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Feb 24 18:01:18 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Application/login.scala.html
                    HASH: d0db1e074389304d6d73b91bba5968b68b7823b8
                    MATRIX: 551->1|697->59|724->77|755->79|768->85|802->87|837->93|874->122|908->124|948->134|1110->274|1151->284|1314->425|1396->476
                    LINES: 19->1|23->1|24->3|25->4|25->4|25->4|26->5|26->5|26->5|27->6|31->10|32->11|36->15|38->17
                    -- GENERATED --
                */
            