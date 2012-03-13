
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
object publicMenue extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.Html] {

    /**/
    def apply():play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.1*/("""<a href="/">Home</a>
<a href="/login">Login</a>
<a href="/users/new">Sign up</a>
<a href="/users">Userlist</a>"""))}
    }
    
    def render() = apply()
    
    def f:(() => play.api.templates.Html) = () => apply()
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Feb 27 15:50:58 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/publicMenue.scala.html
                    HASH: 2fececc2b1d1751e03ad6a313958ee192d745e27
                    MATRIX: 570->0
                    LINES: 22->1
                    -- GENERATED --
                */
            