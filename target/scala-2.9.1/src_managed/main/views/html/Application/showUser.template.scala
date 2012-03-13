
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
object showUser extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[models.User,Flash,Session,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user: models.User)(implicit flash: Flash, session: Session):play.api.templates.Html = {
        _display_ {
def /*2.2*/menue/*2.7*/() = {{
    session.get("user").map(u => privateMenue(session)).getOrElse(publicMenue())
}};
Seq(format.raw/*1.62*/("""
"""),format.raw/*4.2*/("""
"""),_display_(Seq(/*5.2*/main(menue())/*5.15*/ {_display_(Seq(format.raw/*5.17*/("""
    <div>
        <h3>Profile of """),_display_(Seq(/*7.25*/user/*7.29*/.username)),format.raw/*7.38*/("""</h3>
    </div>
    <div>
        Gender: """),_display_(Seq(/*10.18*/{user.sex match {
            case "q" => "Queer"
            case "m" => "Male"
            case "f" => "Female"
            case "n" => "No gender specified"
        }})),format.raw/*15.11*/("""
    </div>

    <div>
        Born on """),_display_(Seq(/*19.18*/user/*19.22*/.dateOfBirth)),format.raw/*19.34*/("""
    </div>

    <div>
        Wishes to die on """),_display_(Seq(/*23.27*/user/*23.31*/.dateOfDeath)),format.raw/*23.43*/("""
    </div>

    <div>
        About: """),_display_(Seq(/*27.17*/user/*27.21*/.description)),format.raw/*27.33*/("""
    </div>

    <div>
        Registered on """),_display_(Seq(/*31.24*/user/*31.28*/.registrationDate)),format.raw/*31.45*/("""
    </div>
    
    """),_display_(Seq(/*34.6*/if(session.get("user") != None)/*34.37*/ {_display_(Seq(format.raw/*34.39*/("""
        <a href="/users/"""),_display_(Seq(/*35.26*/user/*35.30*/.username)),format.raw/*35.39*/("""/messages/new">Write message</a>
    """)))})),format.raw/*36.6*/("""
""")))})))}
    }
    
    def render(user:models.User,flash:Flash,session:Session) = apply(user)(flash,session)
    
    def f:((models.User) => (Flash,Session) => play.api.templates.Html) = (user) => (flash,session) => apply(user)(flash,session)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Feb 27 15:50:09 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Application/showUser.scala.html
                    HASH: 2eb5694ff333c01e5a3d20088ee16928ee67eea1
                    MATRIX: 539->1|659->63|671->68|786->61|813->158|844->160|865->173|899->175|964->210|976->214|1006->223|1081->267|1273->437|1344->477|1357->481|1391->493|1471->542|1484->546|1518->558|1588->597|1601->601|1635->613|1712->659|1725->663|1764->680|1816->702|1856->733|1891->735|1948->761|1961->765|1992->774|2061->812
                    LINES: 19->1|21->2|21->2|24->1|25->4|26->5|26->5|26->5|28->7|28->7|28->7|31->10|36->15|40->19|40->19|40->19|44->23|44->23|44->23|48->27|48->27|48->27|52->31|52->31|52->31|55->34|55->34|55->34|56->35|56->35|56->35|57->36
                    -- GENERATED --
                */
            