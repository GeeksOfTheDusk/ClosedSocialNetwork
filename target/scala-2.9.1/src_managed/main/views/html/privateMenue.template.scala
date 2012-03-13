
package views.html

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
object privateMenue extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Session,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(session: Session):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.20*/("""
<a href="/">Home</a>
<a href="/users/me">My Profile</a>
<a href="/users/me/messages">Private messages</a>
<a href="/users">Userlist</a>
<a href="/logout">Logout</a>"""))}
    }
    
    def render(session:Session) = apply(session)
    
    def f:((Session) => play.api.templates.Html) = (session) => apply(session)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Feb 27 15:50:58 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/privateMenue.scala.html
                    HASH: c4d2470863b74d50a469df656b99eff77bf7d201
                    MATRIX: 513->1|603->19
                    LINES: 19->1|22->1
                    -- GENERATED --
                */
            