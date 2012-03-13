
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
object listMessages extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[List[models.PrivateMessage],play.api.mvc.Request[AnyContent],Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(messages: List[models.PrivateMessage])(implicit request: play.api.mvc.Request[AnyContent], flash: Flash):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.107*/("""
"""),_display_(Seq(/*2.2*/main(privateMenue(request.session))/*2.37*/{_display_(Seq(format.raw/*2.38*/("""
    """),_display_(Seq(/*3.6*/for(m <- messages) yield /*3.24*/ {_display_(Seq(format.raw/*3.26*/("""
        <div>
            <a href="/users/me/messages/"""),_display_(Seq(/*5.42*/m/*5.43*/.id)),format.raw/*5.46*/("""">"""),_display_(Seq(/*5.49*/m/*5.50*/.title)),format.raw/*5.56*/("""</a>
        </div>
    """)))})),format.raw/*7.6*/("""
""")))})))}
    }
    
    def render(messages:List[models.PrivateMessage],request:play.api.mvc.Request[AnyContent],flash:Flash) = apply(messages)(request,flash)
    
    def f:((List[models.PrivateMessage]) => (play.api.mvc.Request[AnyContent],Flash) => play.api.templates.Html) = (messages) => (request,flash) => apply(messages)(request,flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Feb 27 14:10:51 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Private/listMessages.scala.html
                    HASH: 46997e3f621450cc2fe657ef2bb46b18f2da17be
                    MATRIX: 580->1|758->106|789->108|832->143|865->144|900->150|933->168|967->170|1053->226|1062->227|1086->230|1119->233|1128->234|1155->240|1210->265
                    LINES: 19->1|22->1|23->2|23->2|23->2|24->3|24->3|24->3|26->5|26->5|26->5|26->5|26->5|26->5|28->7
                    -- GENERATED --
                */
            