
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
object editUserForm extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Form[scala.Tuple6[scala.Tuple3[String, String, String], java.util.Date, java.util.Date, String, Boolean, Boolean]],models.User,Flash,Session,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(userForm: Form[((String, String, String), java.util.Date, java.util.Date, String, Boolean, Boolean)], user: models.User)(implicit flash: Flash, session: Session):play.api.templates.Html = {
        _display_ {import helper._


Seq(format.raw/*1.164*/("""
"""),_display_(Seq(/*3.2*/main(privateMenue(session))/*3.29*/ {_display_(Seq(format.raw/*3.31*/("""
    """),_display_(Seq(/*4.6*/form(routes.Private.saveUser())/*4.37*/{_display_(Seq(format.raw/*4.38*/("""
        <h3>Editing """),_display_(Seq(/*5.22*/user/*5.26*/.username)),format.raw/*5.35*/("""</h3>
        <input type="hidden" name="user.username" value=""""),_display_(Seq(/*6.59*/user/*6.63*/.username)),format.raw/*6.72*/(""""/>
        """),_display_(Seq(/*7.10*/inputPassword(
            userForm("user.new"),
            '_label -> "New Password: "
        ))),format.raw/*10.10*/("""

        """),_display_(Seq(/*12.10*/inputPassword(
            userForm("user.old"),
            '_label -> "Old Password: ",
            '_help -> "Only needed when changing passwords.",
            '_error -> userForm.error("user")
        ))),format.raw/*17.10*/("""

        """),_display_(Seq(/*19.10*/inputDate(
            userForm("bday"),
            '_label -> "Date of Birth: "
        ))),format.raw/*22.10*/("""

        """),_display_(Seq(/*24.10*/inputDate(
            userForm("dday"),
            '_label -> "Wished date of death: "
        ))),format.raw/*27.10*/("""

        """),_display_(Seq(/*29.10*/textarea(
            userForm("about"),
            '_label -> "About you: ",
            'rows -> 15,
            'cols -> 100
        ))),format.raw/*34.10*/("""

        """),_display_(Seq(/*36.10*/checkbox(
            userForm("anonym"),
            '_label -> "Anonym?",
            '_help -> ""
        ))),format.raw/*40.10*/("""
        <input type="submit" value="Save"/>
    """)))})),format.raw/*42.6*/("""
""")))})))}
    }
    
    def render(userForm:Form[scala.Tuple6[scala.Tuple3[String, String, String], java.util.Date, java.util.Date, String, Boolean, Boolean]],user:models.User,flash:Flash,session:Session) = apply(userForm,user)(flash,session)
    
    def f:((Form[scala.Tuple6[scala.Tuple3[String, String, String], java.util.Date, java.util.Date, String, Boolean, Boolean]],models.User) => (Flash,Session) => play.api.templates.Html) = (userForm,user) => (flash,session) => apply(userForm,user)(flash,session)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Feb 29 17:28:40 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Private/editUserForm.scala.html
                    HASH: f09f8f0f6f3c5203b063d4a07a4f4724cad28f86
                    MATRIX: 654->1|905->163|936->182|971->209|1005->211|1040->217|1079->248|1112->249|1164->271|1176->275|1206->284|1300->348|1312->352|1342->361|1385->374|1505->472|1547->483|1776->690|1818->701|1931->792|1973->803|2093->901|2135->912|2295->1050|2337->1061|2469->1171|2550->1221
                    LINES: 19->1|23->1|24->3|24->3|24->3|25->4|25->4|25->4|26->5|26->5|26->5|27->6|27->6|27->6|28->7|31->10|33->12|38->17|40->19|43->22|45->24|48->27|50->29|55->34|57->36|61->40|63->42
                    -- GENERATED --
                */
            