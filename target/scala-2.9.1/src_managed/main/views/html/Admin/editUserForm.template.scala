
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
object editUserForm extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Form[scala.Tuple6[scala.Tuple2[String, String], java.util.Date, java.util.Date, String, Boolean, Boolean]],models.User,Session,Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(userform: Form[((String, String), java.util.Date, java.util.Date, String, Boolean, Boolean)], user: models.User)(implicit session: Session, flash: Flash):play.api.templates.Html = {
        _display_ {import helper._


Seq(format.raw/*1.156*/("""
"""),_display_(Seq(/*3.2*/main(privateMenue(session))/*3.29*/{_display_(Seq(format.raw/*3.30*/("""
    """),_display_(Seq(/*4.6*/form(routes.Admin.editUser(user.id))/*4.42*/ {_display_(Seq(format.raw/*4.44*/("""
        Edit """),_display_(Seq(/*5.15*/user/*5.19*/.username)),format.raw/*5.28*/("""
        """),_display_(Seq(/*6.10*/inputPassword(userform("user.password"), '_label -> "Password"))),format.raw/*6.73*/("""

        """),_display_(Seq(/*8.10*/inputDate(
            userform("bday"),
            '_label -> "Date of Birth"
        ))),format.raw/*11.10*/("""

        """),_display_(Seq(/*13.10*/inputDate(
            userform("dday"),
            '_label -> "Wished date of death"
        ))),format.raw/*16.10*/("""

        """),_display_(Seq(/*18.10*/textarea(
            userform("about"),
            '_label -> "About you",
            'rows -> 15,
            'cols -> 100
        ))),format.raw/*23.10*/("""

        """),_display_(Seq(/*25.10*/checkbox(
            userform("anonym"),
            '_label -> "Anonym?",
            '_help -> "Wanna be anonym?"
        ))),format.raw/*29.10*/("""

        """),_display_(Seq(/*31.10*/checkbox(
            userform("admin"),
            '_label -> "Is Admin?",
            '_help -> "Wanna be admin?"
        ))),format.raw/*35.10*/("""
        <input type="submit" value="Save"/>
    """)))})),format.raw/*37.6*/("""
""")))})))}
    }
    
    def render(userform:Form[scala.Tuple6[scala.Tuple2[String, String], java.util.Date, java.util.Date, String, Boolean, Boolean]],user:models.User,session:Session,flash:Flash) = apply(userform,user)(session,flash)
    
    def f:((Form[scala.Tuple6[scala.Tuple2[String, String], java.util.Date, java.util.Date, String, Boolean, Boolean]],models.User) => (Session,Flash) => play.api.templates.Html) = (userform,user) => (session,flash) => apply(userform,user)(session,flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Mar 13 23:26:09 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Admin/editUserForm.scala.html
                    HASH: 8303822087f810d6969ae5ea4a6a88b76776f606
                    MATRIX: 644->1|887->155|918->174|953->201|986->202|1021->208|1065->244|1099->246|1144->261|1156->265|1186->274|1226->284|1310->347|1351->358|1462->447|1504->458|1622->554|1664->565|1822->701|1864->712|2012->838|2054->849|2202->975|2283->1025
                    LINES: 19->1|23->1|24->3|24->3|24->3|25->4|25->4|25->4|26->5|26->5|26->5|27->6|27->6|29->8|32->11|34->13|37->16|39->18|44->23|46->25|50->29|52->31|56->35|58->37
                    -- GENERATED --
                */
            