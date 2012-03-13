
package views.html.Private

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
object showMessage extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[models.PrivateMessage,Session,Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(message: models.PrivateMessage)(implicit session:Session, flash: Flash):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.74*/("""
"""),_display_(Seq(/*2.2*/main(privateMenue(session))/*2.29*/{_display_(Seq(format.raw/*2.30*/("""
    """),_display_(Seq(/*3.6*/message/*3.13*/.title)),format.raw/*3.19*/(""" from """),_display_(Seq(/*3.26*/(models.User.findBy("id" -> message.authorID.toString).head.username))),format.raw/*3.95*/(""" on """),_display_(Seq(/*3.100*/message/*3.107*/.writtenAt)),format.raw/*3.117*/("""
    <div>
        """),_display_(Seq(/*5.10*/message/*5.17*/.content)),format.raw/*5.25*/("""
    </div>
    <a href="/users/me/messages/reply/"""),_display_(Seq(/*7.40*/message/*7.47*/.id)),format.raw/*7.50*/("""">Reply</a>
""")))})))}
    }
    
    def render(message:models.PrivateMessage,session:Session,flash:Flash) = apply(message)(session,flash)
    
    def f:((models.PrivateMessage) => (Session,Flash) => play.api.templates.Html) = (message) => (session,flash) => apply(message)(session,flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Feb 27 19:28:39 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Private/showMessage.scala.html
                    HASH: 2632eae16b5e66ce9b154428a01549b0d782d092
                    MATRIX: 548->1|692->73|723->75|758->102|791->103|826->109|841->116|868->122|905->129|995->198|1031->203|1047->210|1079->220|1129->240|1144->247|1173->255|1254->306|1269->313|1293->316
                    LINES: 19->1|22->1|23->2|23->2|23->2|24->3|24->3|24->3|24->3|24->3|24->3|24->3|24->3|26->5|26->5|26->5|28->7|28->7|28->7
                    -- GENERATED --
                */
            