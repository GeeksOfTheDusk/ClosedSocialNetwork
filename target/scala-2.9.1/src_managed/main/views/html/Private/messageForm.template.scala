
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
object messageForm extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Form[scala.Tuple2[String, String]],Long,Flash,Session,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(messageForm: Form[(String, String)], to: Long)(implicit flash: Flash, session: Session):play.api.templates.Html = {
        _display_ {import helper._


Seq(format.raw/*1.90*/("""
"""),_display_(Seq(/*3.2*/main(privateMenue(session))/*3.29*/ {_display_(Seq(format.raw/*3.31*/("""
    """),_display_(Seq(/*4.6*/form(routes.Private.writeMessage(to))/*4.43*/ {_display_(Seq(format.raw/*4.45*/("""
        """),_display_(Seq(/*5.10*/inputText(
            messageForm("title"),
            '_label -> "Title: ",
            '_help -> "May be empty."
        ))),format.raw/*9.10*/("""
        """),_display_(Seq(/*10.10*/textarea(
            messageForm("content"),
            'rows -> 20,
            'cols -> 100,
            '_label -> "Message: ",
            '_error -> messageForm.error("content")
        ))),format.raw/*16.10*/("""

        <input type="submit" value="Send">
    """)))})),format.raw/*19.6*/("""
""")))})))}
    }
    
    def render(messageForm:Form[scala.Tuple2[String, String]],to:Long,flash:Flash,session:Session) = apply(messageForm,to)(flash,session)
    
    def f:((Form[scala.Tuple2[String, String]],Long) => (Flash,Session) => play.api.templates.Html) = (messageForm,to) => (flash,session) => apply(messageForm,to)(flash,session)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Feb 27 15:29:27 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Private/messageForm.scala.html
                    HASH: c89a411815aa5e4d7dc1145752b6a18290cdc2d1
                    MATRIX: 566->1|742->89|773->108|808->135|842->137|877->143|922->180|956->182|996->192|1143->318|1184->328|1400->522|1481->572
                    LINES: 19->1|23->1|24->3|24->3|24->3|25->4|25->4|25->4|26->5|30->9|31->10|37->16|40->19
                    -- GENERATED --
                */
            