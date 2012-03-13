// @SOURCE:/Users/waldemar/webapps/NSA/conf/routes
// @HASH:8c6e9ec9e8dc781bebc7648963fe50a3c93722a0
// @DATE:Tue Mar 13 23:19:03 CET 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {


// @LINE:6
val controllers_Private_index0 = Route("GET", PathPattern(List(StaticPart("/"))))
                    

// @LINE:7
val controllers_Admin_index1 = Route("GET", PathPattern(List(StaticPart("/admin"))))
                    

// @LINE:8
val controllers_Admin_newUser2 = Route("GET", PathPattern(List(StaticPart("/admin/users/new"))))
                    

// @LINE:9
val controllers_Admin_editUserForm3 = Route("GET", PathPattern(List(StaticPart("/admin/users/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:10
val controllers_Application_index4 = Route("GET", PathPattern(List(StaticPart("/public"))))
                    

// @LINE:11
val controllers_Application_login5 = Route("GET", PathPattern(List(StaticPart("/login"))))
                    

// @LINE:12
val controllers_Private_logout6 = Route("GET", PathPattern(List(StaticPart("/logout"))))
                    

// @LINE:13
val controllers_Private_newMessage7 = Route("GET", PathPattern(List(StaticPart("/users/"),DynamicPart("name", """[^/]+"""),StaticPart("/messages/new"))))
                    

// @LINE:14
val controllers_Application_signup8 = Route("GET", PathPattern(List(StaticPart("/users/new"))))
                    

// @LINE:15
val controllers_Private_me9 = Route("GET", PathPattern(List(StaticPart("/users/me"))))
                    

// @LINE:16
val controllers_Application_user10 = Route("GET", PathPattern(List(StaticPart("/users/"),DynamicPart("name", """[^/]+"""))))
                    

// @LINE:17
val controllers_Application_users11 = Route("GET", PathPattern(List(StaticPart("/users"))))
                    

// @LINE:18
val controllers_Private_listPm12 = Route("GET", PathPattern(List(StaticPart("/users/me/messages"))))
                    

// @LINE:19
val controllers_Private_editUser13 = Route("GET", PathPattern(List(StaticPart("/users/me/edit"))))
                    

// @LINE:20
val controllers_Private_reply14 = Route("GET", PathPattern(List(StaticPart("/users/me/messages/reply/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:21
val controllers_Private_showPm15 = Route("GET", PathPattern(List(StaticPart("/users/me/messages/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:24
val controllers_Admin_delete16 = Route("POST", PathPattern(List(StaticPart("/admin/users/delete/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:25
val controllers_Admin_createUser17 = Route("POST", PathPattern(List(StaticPart("/admin/users/create"))))
                    

// @LINE:26
val controllers_Admin_editUser18 = Route("POST", PathPattern(List(StaticPart("/admin/users/edit/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:27
val controllers_Application_auth19 = Route("POST", PathPattern(List(StaticPart("/auth"))))
                    

// @LINE:28
val controllers_Application_newUser20 = Route("POST", PathPattern(List(StaticPart("/newUser"))))
                    

// @LINE:29
val controllers_Private_writeMessage21 = Route("POST", PathPattern(List(StaticPart("/sendPm"))))
                    

// @LINE:30
val controllers_Private_createKey22 = Route("POST", PathPattern(List(StaticPart("/createKey"))))
                    

// @LINE:31
val controllers_Private_saveUser23 = Route("POST", PathPattern(List(StaticPart("/saveUser"))))
                    

// @LINE:34
val controllers_Assets_at24 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    
def documentation = List(("""GET""","""/""","""controllers.Private.index"""),("""GET""","""/admin""","""controllers.Admin.index"""),("""GET""","""/admin/users/new""","""controllers.Admin.newUser"""),("""GET""","""/admin/users/$id<[^/]+>""","""controllers.Admin.editUserForm(id:Long)"""),("""GET""","""/public""","""controllers.Application.index"""),("""GET""","""/login""","""controllers.Application.login"""),("""GET""","""/logout""","""controllers.Private.logout"""),("""GET""","""/users/$name<[^/]+>/messages/new""","""controllers.Private.newMessage(name:String)"""),("""GET""","""/users/new""","""controllers.Application.signup"""),("""GET""","""/users/me""","""controllers.Private.me"""),("""GET""","""/users/$name<[^/]+>""","""controllers.Application.user(name:String)"""),("""GET""","""/users""","""controllers.Application.users"""),("""GET""","""/users/me/messages""","""controllers.Private.listPm"""),("""GET""","""/users/me/edit""","""controllers.Private.editUser"""),("""GET""","""/users/me/messages/reply/$id<[^/]+>""","""controllers.Private.reply(id:Long)"""),("""GET""","""/users/me/messages/$id<[^/]+>""","""controllers.Private.showPm(id:Long)"""),("""POST""","""/admin/users/delete/$id<[^/]+>""","""controllers.Admin.delete(id:Long)"""),("""POST""","""/admin/users/create""","""controllers.Admin.createUser"""),("""POST""","""/admin/users/edit/$id<[^/]+>""","""controllers.Admin.editUser(id:Long)"""),("""POST""","""/auth""","""controllers.Application.auth"""),("""POST""","""/newUser""","""controllers.Application.newUser"""),("""POST""","""/sendPm""","""controllers.Private.writeMessage(to:Long)"""),("""POST""","""/createKey""","""controllers.Private.createKey"""),("""POST""","""/saveUser""","""controllers.Private.saveUser"""),("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""))
             
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Private_index0(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.index, HandlerDef(this, "controllers.Private", "index", Nil))
   }
}
                    

// @LINE:7
case controllers_Admin_index1(params) => {
   call { 
        invokeHandler(_root_.controllers.Admin.index, HandlerDef(this, "controllers.Admin", "index", Nil))
   }
}
                    

// @LINE:8
case controllers_Admin_newUser2(params) => {
   call { 
        invokeHandler(_root_.controllers.Admin.newUser, HandlerDef(this, "controllers.Admin", "newUser", Nil))
   }
}
                    

// @LINE:9
case controllers_Admin_editUserForm3(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.Admin.editUserForm(id), HandlerDef(this, "controllers.Admin", "editUserForm", Seq(classOf[Long])))
   }
}
                    

// @LINE:10
case controllers_Application_index4(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.index, HandlerDef(this, "controllers.Application", "index", Nil))
   }
}
                    

// @LINE:11
case controllers_Application_login5(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.login, HandlerDef(this, "controllers.Application", "login", Nil))
   }
}
                    

// @LINE:12
case controllers_Private_logout6(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.logout, HandlerDef(this, "controllers.Private", "logout", Nil))
   }
}
                    

// @LINE:13
case controllers_Private_newMessage7(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(_root_.controllers.Private.newMessage(name), HandlerDef(this, "controllers.Private", "newMessage", Seq(classOf[String])))
   }
}
                    

// @LINE:14
case controllers_Application_signup8(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.signup, HandlerDef(this, "controllers.Application", "signup", Nil))
   }
}
                    

// @LINE:15
case controllers_Private_me9(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.me, HandlerDef(this, "controllers.Private", "me", Nil))
   }
}
                    

// @LINE:16
case controllers_Application_user10(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(_root_.controllers.Application.user(name), HandlerDef(this, "controllers.Application", "user", Seq(classOf[String])))
   }
}
                    

// @LINE:17
case controllers_Application_users11(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.users, HandlerDef(this, "controllers.Application", "users", Nil))
   }
}
                    

// @LINE:18
case controllers_Private_listPm12(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.listPm, HandlerDef(this, "controllers.Private", "listPm", Nil))
   }
}
                    

// @LINE:19
case controllers_Private_editUser13(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.editUser, HandlerDef(this, "controllers.Private", "editUser", Nil))
   }
}
                    

// @LINE:20
case controllers_Private_reply14(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.Private.reply(id), HandlerDef(this, "controllers.Private", "reply", Seq(classOf[Long])))
   }
}
                    

// @LINE:21
case controllers_Private_showPm15(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.Private.showPm(id), HandlerDef(this, "controllers.Private", "showPm", Seq(classOf[Long])))
   }
}
                    

// @LINE:24
case controllers_Admin_delete16(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.Admin.delete(id), HandlerDef(this, "controllers.Admin", "delete", Seq(classOf[Long])))
   }
}
                    

// @LINE:25
case controllers_Admin_createUser17(params) => {
   call { 
        invokeHandler(_root_.controllers.Admin.createUser, HandlerDef(this, "controllers.Admin", "createUser", Nil))
   }
}
                    

// @LINE:26
case controllers_Admin_editUser18(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.Admin.editUser(id), HandlerDef(this, "controllers.Admin", "editUser", Seq(classOf[Long])))
   }
}
                    

// @LINE:27
case controllers_Application_auth19(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.auth, HandlerDef(this, "controllers.Application", "auth", Nil))
   }
}
                    

// @LINE:28
case controllers_Application_newUser20(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.newUser, HandlerDef(this, "controllers.Application", "newUser", Nil))
   }
}
                    

// @LINE:29
case controllers_Private_writeMessage21(params) => {
   call(params.fromQuery[Long]("to", None)) { (to) =>
        invokeHandler(_root_.controllers.Private.writeMessage(to), HandlerDef(this, "controllers.Private", "writeMessage", Seq(classOf[Long])))
   }
}
                    

// @LINE:30
case controllers_Private_createKey22(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.createKey, HandlerDef(this, "controllers.Private", "createKey", Nil))
   }
}
                    

// @LINE:31
case controllers_Private_saveUser23(params) => {
   call { 
        invokeHandler(_root_.controllers.Private.saveUser, HandlerDef(this, "controllers.Private", "saveUser", Nil))
   }
}
                    

// @LINE:34
case controllers_Assets_at24(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    
}
    
}
                