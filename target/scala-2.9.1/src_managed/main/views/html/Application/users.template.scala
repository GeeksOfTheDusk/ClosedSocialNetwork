
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
object users extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[List[models.User],Session,Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(users: List[models.User])(implicit session: Session, flash: Flash):play.api.templates.Html = {
        _display_ {
def /*3.2*/menue/*3.7*/() = {{
    session.get("user").map(u => privateMenue(session)).getOrElse(publicMenue())
}};
Seq(format.raw/*1.69*/("""

"""),format.raw/*5.2*/("""
"""),_display_(Seq(/*6.2*/main(menue())/*6.15*/ {_display_(Seq(format.raw/*6.17*/("""
    """),_display_(Seq(/*7.6*/for(user <- users) yield /*7.24*/ {_display_(Seq(format.raw/*7.26*/("""
        <div><a href="/users/"""),_display_(Seq(/*8.31*/user/*8.35*/.username)),format.raw/*8.44*/("""">"""),_display_(Seq(/*8.47*/user/*8.51*/.username)),format.raw/*8.60*/("""</a></div>
    """)))})),format.raw/*9.6*/("""
""")))})))}
    }
    
    def render(users:List[models.User],session:Session,flash:Flash) = apply(users)(session,flash)
    
    def f:((List[models.User]) => (Session,Flash) => play.api.templates.Html) = (users) => (session,flash) => apply(users)(session,flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Feb 24 19:58:51 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Application/users.scala.html
                    HASH: e0938b6dfba09903850a771c3702d0c8b1afe93b
                    MATRIX: 542->1|669->71|681->76|796->68|824->166|855->168|876->181|910->183|945->189|978->207|1012->209|1073->240|1085->244|1115->253|1148->256|1160->260|1190->269|1236->285
                    LINES: 19->1|21->3|21->3|24->1|26->5|27->6|27->6|27->6|28->7|28->7|28->7|29->8|29->8|29->8|29->8|29->8|29->8|30->9
                    -- GENERATED --
                */
            