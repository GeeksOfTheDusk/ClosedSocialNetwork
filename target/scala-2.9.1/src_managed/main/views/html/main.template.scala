
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
object main extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Html,Html,Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(menue: Html = publicMenue())(content: Html)(implicit flash: Flash):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.69*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>Neo Suicide Apartment</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq(/*8.54*/routes/*8.60*/.Assets.at("stylesheets/main.css"))),format.raw/*8.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq(/*9.59*/routes/*9.65*/.Assets.at("images/favicon.png"))),format.raw/*9.97*/("""">
        <script src=""""),_display_(Seq(/*10.23*/routes/*10.29*/.Assets.at("javascripts/jquery-1.7.1.min.js"))),format.raw/*10.74*/("""" type="text/javascript"></script>
    </head>
    <body>
        <h1>Neo Suicide Apartment</h1>
        """),_display_(Seq(/*14.10*/menue)),format.raw/*14.15*/("""
        <hr />
        """),_display_(Seq(/*16.10*/printFlash(flash))),format.raw/*16.27*/("""
        """),_display_(Seq(/*17.10*/content)),format.raw/*17.17*/("""
    </body>
</html>
"""))}
    }
    
    def render(menue:Html,content:Html,flash:Flash) = apply(menue)(content)(flash)
    
    def f:((Html) => (Html) => (Flash) => play.api.templates.Html) = (menue) => (content) => (flash) => apply(menue)(content)(flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Feb 24 19:54:03 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/main.scala.html
                    HASH: 65257e1ba1bf82f1a528d5f584d7966affdc15c1
                    MATRIX: 513->1|652->68|817->203|831->209|886->243|977->304|991->310|1044->342|1100->367|1115->373|1182->418|1319->524|1346->529|1402->554|1441->571|1482->581|1511->588
                    LINES: 19->1|22->1|29->8|29->8|29->8|30->9|30->9|30->9|31->10|31->10|31->10|35->14|35->14|37->16|37->16|38->17|38->17
                    -- GENERATED --
                */
            