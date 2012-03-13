
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
object showProfile extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[models.User,List[models.InvitationKey],Session,Flash,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user: models.User, keys: List[models.InvitationKey])(implicit session:Session, flash: Flash):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.95*/("""
"""),_display_(Seq(/*2.2*/main(privateMenue(session))/*2.29*/ {_display_(Seq(format.raw/*2.31*/("""
    <div>
        <h3>Profile of """),_display_(Seq(/*4.25*/user/*4.29*/.username)),format.raw/*4.38*/("""</h3>
    </div>
    <div>
        Gender: """),_display_(Seq(/*7.18*/{user.sex match {
        case "q" => "Queer"
        case "m" => "Male"
        case "f" => "Female"
        case "n" => "No gender specified"
        }})),format.raw/*12.11*/("""
    </div>

    <div>
        Born on """),_display_(Seq(/*16.18*/user/*16.22*/.dateOfBirth)),format.raw/*16.34*/("""
    </div>

    <div>
        Wishes to die on """),_display_(Seq(/*20.27*/user/*20.31*/.dateOfDeath)),format.raw/*20.43*/("""
    </div>

    <div>
        About: """),_display_(Seq(/*24.17*/user/*24.21*/.description)),format.raw/*24.33*/("""
    </div>

    <div>
        Registered on """),_display_(Seq(/*28.24*/user/*28.28*/.registrationDate)),format.raw/*28.45*/("""
    </div>

    """),_display_(Seq(/*31.6*/if(user.invitedBy > 0)/*31.28*/ {_display_(Seq(format.raw/*31.30*/("""
    <div>
        Infited by <a href="/users/"""),_display_(Seq(/*33.37*/models/*33.43*/.User.findBy("id" -> user.invitedBy.toString).head.username)),format.raw/*33.102*/("""">
        """),_display_(Seq(/*34.10*/models/*34.16*/.User.findBy("id" -> user.invitedBy.toString).head.username)),format.raw/*34.75*/("""</a>
    </div>
    """)))})),format.raw/*36.6*/("""
    """),_display_(Seq(/*37.6*/for(key <- keys) yield /*37.22*/ {_display_(Seq(format.raw/*37.24*/("""
        <p>"""),_display_(Seq(/*38.13*/key/*38.16*/.key_string)),format.raw/*38.27*/("""</p>
    """)))})),format.raw/*39.6*/("""
    """),_display_(Seq(/*40.6*/if(keys.length < 3)/*40.25*/ {_display_(Seq(format.raw/*40.27*/("""
        """),_display_(Seq(/*41.10*/helper/*41.16*/.form(routes.Private.createKey())/*41.49*/{_display_(Seq(format.raw/*41.50*/("""<input type="submit" value="Generate key">""")))})),format.raw/*41.93*/("""
    """)))})),format.raw/*42.6*/("""
    <a href="/users/me/edit">Edit profile</a>
""")))})))}
    }
    
    def render(user:models.User,keys:List[models.InvitationKey],session:Session,flash:Flash) = apply(user,keys)(session,flash)
    
    def f:((models.User,List[models.InvitationKey]) => (Session,Flash) => play.api.templates.Html) = (user,keys) => (session,flash) => apply(user,keys)(session,flash)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Feb 29 12:12:23 CET 2012
                    SOURCE: /Users/waldemar/webapps/NSA/app/views/Private/showProfile.scala.html
                    HASH: acf294b6c7c92491d473e4b235d2470d2b3a2f99
                    MATRIX: 565->1|730->94|761->96|796->123|830->125|895->160|907->164|937->173|1011->217|1187->371|1258->411|1271->415|1305->427|1385->476|1398->480|1432->492|1502->531|1515->535|1549->547|1626->593|1639->597|1678->614|1726->632|1757->654|1792->656|1870->703|1885->709|1967->768|2010->780|2025->786|2106->845|2158->866|2194->872|2226->888|2261->890|2305->903|2317->906|2350->917|2391->927|2427->933|2455->952|2490->954|2531->964|2546->970|2588->1003|2622->1004|2697->1047|2734->1053
                    LINES: 19->1|22->1|23->2|23->2|23->2|25->4|25->4|25->4|28->7|33->12|37->16|37->16|37->16|41->20|41->20|41->20|45->24|45->24|45->24|49->28|49->28|49->28|52->31|52->31|52->31|54->33|54->33|54->33|55->34|55->34|55->34|57->36|58->37|58->37|58->37|59->38|59->38|59->38|60->39|61->40|61->40|61->40|62->41|62->41|62->41|62->41|62->41|63->42
                    -- GENERATED --
                */
            