
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
object newUserForm extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Form[scala.Tuple6[scala.Tuple2[String, String], java.util.Date, java.util.Date, String, Boolean, Boolean]],Session,Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(userform: Form[((String, String), java.util.Date, java.util.Date, String, Boolean, Boolean)])(implicit session: Session, flash: Flash):play.api.templates.Html = {
        _display_ {import helper._


Seq(format.raw/*1.137*/("""
"""),_display_(Seq(/*3.2*/main(privateMenue(session))/*3.29*/{_display_(Seq(format.raw/*3.30*/("""
    """),_display_(Seq(/*4.6*/form(routes.Admin.createUser)/*4.35*/ {_display_(Seq(format.raw/*4.37*/("""
        """),_display_(Seq(/*5.10*/inputText(userform("user.username"), '_label -> "Username", '_error -> userform.error("user.username")))),format.raw/*5.113*/("""

        """),_display_(Seq(/*7.10*/inputPassword(userform("user.password"), '_label -> "Password"))),format.raw/*7.73*/("""

        """),_display_(Seq(/*9.10*/inputDate(
            userform("bday"),
            '_label -> "Date of Birth"
        ))),format.raw/*12.10*/("""

        """),_display_(Seq(/*14.10*/inputDate(
            userform("dday"),
            '_label -> "Wished date of death"
        ))),format.raw/*17.10*/("""

        """),_display_(Seq(/*19.10*/textarea(
            userform("about"),
            '_label -> "About you",
            'rows -> 15,
            'cols -> 100
        ))),format.raw/*24.10*/("""

        """),_display_(Seq(/*26.10*/checkbox(
            userform("anonym"),
            '_label -> "Anonym?",
            '_help -> "Wanna be anonym?"
        ))),format.raw/*30.10*/("""

        """),_display_(Seq(/*32.10*/checkbox(
            userform("admin"),
            '_label -> "Is Admin?",
            '_help -> "Wanna be admin?"
        ))),format.raw/*36.10*/("""
        <input type="submit" value="Create"/>
    """)))})),format.raw/*38.6*/("""
""")))})))}
    }
    
    def render(userform:Form[scala.Tuple6[scala.Tuple2[String, String], java.util.Date, java.util.Date, String, Boolean, Boolean]],session:Session,flash:Flash) = apply(userform)(session,flash)
    
    def f:((Form[scala.Tuple6[scala.Tuple2[String, String], java.util.Date, java.util.Date, String, Boolean, Boolean]]) => (Session,Flash) => play.api.templates.Html) = (userform) => (session,flash) => apply(userform)(session,flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Mar 13 23:25:13 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Admin/newUserForm.scala.html
                    HASH: 4d846778c98e02410182af65986597d29ce1762a
                    MATRIX: 631->1|855->136|886->155|921->182|954->183|989->189|1026->218|1060->220|1100->230|1225->333|1266->344|1350->407|1391->418|1502->507|1544->518|1662->614|1704->625|1862->761|1904->772|2052->898|2094->909|2242->1035|2325->1087
                    LINES: 19->1|23->1|24->3|24->3|24->3|25->4|25->4|25->4|26->5|26->5|28->7|28->7|30->9|33->12|35->14|38->17|40->19|45->24|47->26|51->30|53->32|57->36|59->38
                    -- GENERATED --
                */
            