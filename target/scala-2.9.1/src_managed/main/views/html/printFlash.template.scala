
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
object printFlash extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(flash: Flash):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.16*/("""

"""),_display_(Seq(/*3.2*/for(m <- flash.data) yield /*3.22*/ {_display_(Seq(format.raw/*3.24*/("""
    <div>"""),_display_(Seq(/*4.11*/m/*4.12*/._2)),format.raw/*4.15*/("""</div>
    <div>"""),_display_(Seq(/*5.11*/flash/*5.16*/.get("pmcount"))),format.raw/*5.31*/("""</div>
""")))})))}
    }
    
    def render(flash:Flash) = apply(flash)
    
    def f:((Flash) => play.api.templates.Html) = (flash) => apply(flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Feb 24 20:12:21 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/printFlash.scala.html
                    HASH: f64f5b8fde3ef68ef9018bc0c5bfbe96a00c1f24
                    MATRIX: 509->1|595->15|627->18|662->38|696->40|737->51|746->52|770->55|817->72|830->77|866->92
                    LINES: 19->1|22->1|24->3|24->3|24->3|25->4|25->4|25->4|26->5|26->5|26->5
                    -- GENERATED --
                */
            