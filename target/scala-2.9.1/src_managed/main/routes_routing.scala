// @SOURCE:/Users/waldemar/webapps/NSA/conf/routes
// @HASH:b67ae36a603b6bce15cfa1ac348e2ff14e0b21bf
// @DATE:Wed Feb 29 12:40:58 CET 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {


// @LINE:6
val controllers_Private_index0 = Route("GET", PathPattern(List(StaticPart("/"))))
                    

// @LINE:7
val controllers_Application_index1 = Route("GET", PathPattern(List(StaticPart("/public"))))
                    

// @LINE:8
val controllers_Application_login2 = Route("GET", PathPattern(List(StaticPart("/login"))))
                    

// @LINE:9
val controllers_Private_logout3 = Route("GET", PathPattern(List(StaticPart("/logout"))))
                    

// @LINE:10
val controllers_Private_newMessage4 = Route("GET", PathPattern(List(StaticPart("/users/"),DynamicPart("name", """[^/]+"""),StaticPart("/messages/new"))))
                    

// @LINE:11
val controllers_Application_signup5 = Route("GET", PathPattern(List(StaticPart("/users/new"))))
                    

// @LINE:12
val controllers_Private_me6 = Route("GET", PathPattern(List(StaticPart("/users/me"))))
                    

// @LINE:13
val controllers_Application_user7 = Route("GET", PathPattern(List(StaticPart("/users/"),DynamicPart("name", """[^/]+"""))))
                    

// @LINE:14
val controllers_Application_users8 = Route("GET", PathPattern(List(StaticPart("/users"))))
                    

// @LINE:15
val controllers_Private_listPm9 = Route("GET", PathPattern(List(StaticPart("/users/me/messages"))))
                    

// @LINE:16
val controllers_Private_editUser10 = Route("GET", PathPattern(List(StaticPart("/users/me/edit"))))
                    

// @LINE:17
val controllers_Private_reply11 = Route("GET", PathPattern(List(StaticPart("/users/me/messages/reply/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:18
val controllers_Private_showPm12 = Route("GET", PathPattern(List(StaticPart("/users/me/messages/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:19
val controllers_Application_auth13 = Route("POST", PathPattern(List(StaticPart("/auth"))))
                    

// @LINE:20
val controllers_Application_newUser14 = Route("POST", PathPattern(List(StaticPart("/newUser"))))
                    

// @LINE:21
val controllers_Private_writeMessage15 = Route("POST", PathPattern(List(StaticPart("/sendPm"))))
                    

// @LINE:22
val controllers_Private_createKey16 = Route("POST", PathPattern(List(StaticPart("/createKey"))))
                    

// @LINE:23
val controllers_Private_saveUser17 = Route("POST", PathPattern(List(StaticPart("/saveUser"))))
                    

// @LINE:26
val controllers_Assets_at18 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    
def documentation = List(("""GET""","""/""","""controllers.Private.index"""),("""GET""","""/public""","""controllers.Application.index"""),("""GET""","""/login""","""controllers.Application.login"""),("""GET""","""/logout""","""controllers.Private.logout"""),("""GET""","""/users/$name<[^/]+>/messages/new""","""controllers.Private.newMessage(name:String)"""),("""GET""","""/users/new""","""controllers.Application.signup"""),("""GET""","""/users/me""","""controllers.Private.me"""),("""GET""","""/users/$name<[^/]+>""","""controllers.Application.user(name:String)"""),("""GET""","""/users""","""controllers.Application.users"""),("""GET""","""/users/me/messages""","""controllers.Private.listPm"""),("""GET""","""/users/me/edit""","""controllers.Private.editUser"""),("""GET""","""/users/me/messages/reply/$id<[^/]+>""","""controllers.Private.reply(id:Long)"""),("""GET""","""/users/me/messages/$id<[^/]+>""","""controllers.Private.showPm(id:Long)"""),("""POST""","""/auth""","""controllers.Application.auth"""),("""POST""","""/newUser""","""controllers.Application.newUser"""),("""POST""","""/sendPm""","""controllers.Private.writeMessage(to:Long)"""),("""POST""","""/createKey""","""controllers.Private.createKey"""),("""POST""","""/saveUser""","""controllers.Private.saveUser"""),("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""))
             
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Private_index0(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.index, HandlerDef(this, "controllers.Private", "index", Nil))
   }
}
                    

// @LINE:7
case controllers_Application_index1(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.index, HandlerDef(this, "controllers.Application", "index", Nil))
   }
}
                    

// @LINE:8
case controllers_Application_login2(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.login, HandlerDef(this, "controllers.Application", "login", Nil))
   }
}
                    

// @LINE:9
case controllers_Private_logout3(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.logout, HandlerDef(this, "controllers.Private", "logout", Nil))
   }
}
                    

// @LINE:10
case controllers_Private_newMessage4(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(_root_.controllers.Private.newMessage(name), HandlerDef(this, "controllers.Private", "newMessage", Seq(classOf[String])))
   }
}
                    

// @LINE:11
case controllers_Application_signup5(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.signup, HandlerDef(this, "controllers.Application", "signup", Nil))
   }
}
                    

// @LINE:12
case controllers_Private_me6(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.me, HandlerDef(this, "controllers.Private", "me", Nil))
   }
}
                    

// @LINE:13
case controllers_Application_user7(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(_root_.controllers.Application.user(name), HandlerDef(this, "controllers.Application", "user", Seq(classOf[String])))
   }
}
                    

// @LINE:14
case controllers_Application_users8(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.users, HandlerDef(this, "controllers.Application", "users", Nil))
   }
}
                    

// @LINE:15
case controllers_Private_listPm9(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.listPm, HandlerDef(this, "controllers.Private", "listPm", Nil))
   }
}
                    

// @LINE:16
case controllers_Private_editUser10(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.editUser, HandlerDef(this, "controllers.Private", "editUser", Nil))
   }
}
                    

// @LINE:17
case controllers_Private_reply11(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.Private.reply(id), HandlerDef(this, "controllers.Private", "reply", Seq(classOf[Long])))
   }
}
                    

// @LINE:18
case controllers_Private_showPm12(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.Private.showPm(id), HandlerDef(this, "controllers.Private", "showPm", Seq(classOf[Long])))
   }
}
                    

// @LINE:19
case controllers_Application_auth13(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.auth, HandlerDef(this, "controllers.Application", "auth", Nil))
   }
}
                    

// @LINE:20
case controllers_Application_newUser14(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.newUser, HandlerDef(this, "controllers.Application", "newUser", Nil))
   }
}
                    

// @LINE:21
case controllers_Private_writeMessage15(params) => {
   call(params.fromQuery[Long]("to", None)) { (to) =>
        invokeHandler(_root_.controllers.Private.writeMessage(to), HandlerDef(this, "controllers.Private", "writeMessage", Seq(classOf[Long])))
   }
}
                    

// @LINE:22
case controllers_Private_createKey16(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.createKey, HandlerDef(this, "controllers.Private", "createKey", Nil))
   }
}
                    

// @LINE:23
case controllers_Private_saveUser17(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.saveUser, HandlerDef(this, "controllers.Private", "saveUser", Nil))
   }
}
                    

// @LINE:26
case controllers_Assets_at18(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    
}
    
}
                