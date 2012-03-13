
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
<a href="/logout">Logout</a>

"""),_display_(Seq(/*8.2*/if(User.findBy("username" -> session.get("user").get).head.isAdmin)/*8.69*/ {_display_(Seq(format.raw/*8.71*/("""
    <a href="/admin">Admin</a>
""")))})))}
    }
    
    def render(session:Session) = apply(session)
    
    def f:((Session) => play.api.templates.Html) = (session) => apply(session)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Mar 13 17:44:46 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/privateMenue.scala.html
                    HASH: 51084be56cc3ada6acdc42ee1fa6e0c5760e4a7c
                    MATRIX: 513->1|603->19|800->187|875->254|909->256
                    LINES: 19->1|22->1|29->8|29->8|29->8
                    -- GENERATED --
                */
            