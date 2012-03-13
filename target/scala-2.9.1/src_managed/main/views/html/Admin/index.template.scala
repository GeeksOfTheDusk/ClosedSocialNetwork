
package views.html.Admin

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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[List[models.User],Session,Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(users: List[models.User])(implicit session: Session, flash: Flash):play.api.templates.Html = {
        _display_ {import helper._


Seq(format.raw/*1.69*/("""
"""),_display_(Seq(/*3.2*/main(privateMenue(session))/*3.29*/{_display_(Seq(format.raw/*3.30*/("""
    <h3>This is the Admin area.</h3>
    """),_display_(Seq(/*5.6*/for(user <- users) yield /*5.24*/ {_display_(Seq(format.raw/*5.26*/("""
        """),_display_(Seq(/*6.10*/user/*6.14*/.id)),format.raw/*6.17*/(""" """),_display_(Seq(/*6.19*/user/*6.23*/.username)),format.raw/*6.32*/(""" <a href="/admin/users/"""),_display_(Seq(/*6.56*/user/*6.60*/.id)),format.raw/*6.63*/("""">Edit</a>
        """),_display_(Seq(/*7.10*/form(routes.Admin.delete(user.id))/*7.44*/{_display_(Seq(format.raw/*7.45*/("""<input type="submit" value="Delete">""")))})),format.raw/*7.82*/("""
        <br/>
    """)))})),format.raw/*9.6*/("""
    <a href="/admin/users/new">Create new user</a>
""")))})))}
    }
    
    def render(users:List[models.User],session:Session,flash:Flash) = apply(users)(session,flash)
    
    def f:((List[models.User]) => (Session,Flash) => play.api.templates.Html) = (users) => (session,flash) => apply(users)(session,flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Mar 13 20:57:09 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Admin/index.scala.html
                    HASH: aaba433d111df04a588c475946e1a73d344c562f
                    MATRIX: 536->1|691->68|722->87|757->114|790->115|862->158|895->176|929->178|969->188|981->192|1005->195|1037->197|1049->201|1079->210|1133->234|1145->238|1169->241|1219->261|1261->295|1294->296|1362->333|1412->353
                    LINES: 19->1|23->1|24->3|24->3|24->3|26->5|26->5|26->5|27->6|27->6|27->6|27->6|27->6|27->6|27->6|27->6|27->6|28->7|28->7|28->7|28->7|30->9
                    -- GENERATED --
                */
            