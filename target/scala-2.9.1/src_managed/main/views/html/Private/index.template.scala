
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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Long,play.api.mvc.Request[AnyContent],Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(pms: Long)(implicit request: play.api.mvc.Request[AnyContent], flash: Flash):play.api.templates.Html = {
        _display_ {import play.api.Play.current


Seq(format.raw/*1.79*/("""
"""),_display_(Seq(/*3.2*/main(privateMenue(request.session))/*3.37*/ {_display_(Seq(format.raw/*3.39*/("""
    """),_display_(Seq(/*4.6*/if(pms > 0)/*4.17*/{_display_(Seq(format.raw/*4.18*/("""You have """),_display_(Seq(/*4.28*/pms)),format.raw/*4.31*/(""" unread messages.""")))})),format.raw/*4.49*/("""
    <h3>Hello """),_display_(Seq(/*5.16*/request/*5.23*/.session.get("user").get)),format.raw/*5.47*/("""</h3>
""")))})),format.raw/*6.2*/("""
"""))}
    }
    
    def render(pms:Long,request:play.api.mvc.Request[AnyContent],flash:Flash) = apply(pms)(request,flash)
    
    def f:((Long) => (play.api.mvc.Request[AnyContent],Flash) => play.api.templates.Html) = (pms) => (request,flash) => apply(pms)(request,flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Feb 26 14:49:27 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Private/index.scala.html
                    HASH: 5515101556bff22aebc533cd54b9a554ce51e614
                    MATRIX: 550->1|728->78|759->110|802->145|836->147|871->153|890->164|923->165|963->175|987->178|1036->196|1082->212|1097->219|1142->243|1179->250
                    LINES: 19->1|23->1|24->3|24->3|24->3|25->4|25->4|25->4|25->4|25->4|25->4|26->5|26->5|26->5|27->6
                    -- GENERATED --
                */
            