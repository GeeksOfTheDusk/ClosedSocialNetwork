
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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(implicit flash: Flash):play.api.templates.Html = {
        _display_ {import play.api.Play.current


Seq(format.raw/*1.25*/("""
"""),_display_(Seq(/*3.2*/main()/*3.8*/ {_display_(Seq(format.raw/*3.10*/("""
    <h3>Welcome Guest</h3>
""")))})))}
    }
    
    def render(flash:Flash) = apply(flash)
    
    def f:((Flash) => play.api.templates.Html) = (flash) => apply(flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Feb 26 14:49:27 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Application/index.scala.html
                    HASH: a4d8043d5870e3b2f25495a72d1259a1151ef0e3
                    MATRIX: 516->1|640->24|671->56|684->62|718->64
                    LINES: 19->1|23->1|24->3|24->3|24->3
                    -- GENERATED --
                */
            