// @SOURCE:/Users/waldemar/webapps/NSA/conf/routes
// @HASH:8c6e9ec9e8dc781bebc7648963fe50a3c93722a0
// @DATE:Tue Mar 13 23:19:03 CET 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:34
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:9
// @LINE:8
// @LINE:7
class ReverseAdmin {
    


 
// @LINE:26
def editUser(id:Long) = {
   Call("POST", "/admin/users/edit/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:24
def delete(id:Long) = {
   Call("POST", "/admin/users/delete/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:9
def editUserForm(id:Long) = {
   Call("GET", "/admin/users/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:7
def index() = {
   Call("GET", "/admin")
}
                                                        
 
// @LINE:8
def newUser() = {
   Call("GET", "/admin/users/new")
}
                                                        
 
// @LINE:25
def createUser() = {
   Call("POST", "/admin/users/create")
}
                                                        

                      
    
}
                            

// @LINE:28
// @LINE:27
// @LINE:17
// @LINE:16
// @LINE:14
// @LINE:11
// @LINE:10
class ReverseApplication {
    


 
// @LINE:14
def signup() = {
   Call("GET", "/users/new")
}
                                                        
 
// @LINE:16
def user(name:String) = {
   Call("GET", "/users/" + implicitly[PathBindable[String]].unbind("name", name))
}
                                                        
 
// @LINE:11
def login() = {
   Call("GET", "/login")
}
                                                        
 
// @LINE:17
def users() = {
   Call("GET", "/users")
}
                                                        
 
// @LINE:10
def index() = {
   Call("GET", "/public")
}
                                                        
 
// @LINE:28
def newUser() = {
   Call("POST", "/newUser")
}
                                                        
 
// @LINE:27
def auth() = {
   Call("POST", "/auth")
}
                                                        

                      
    
}
                            

// @LINE:34
class ReverseAssets {
    


 
// @LINE:34
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            

// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:15
// @LINE:13
// @LINE:12
// @LINE:6
class ReversePrivate {
    


 
// @LINE:30
def createKey() = {
   Call("POST", "/createKey")
}
                                                        
 
// @LINE:31
def saveUser() = {
   Call("POST", "/saveUser")
}
                                                        
 
// @LINE:12
def logout() = {
   Call("GET", "/logout")
}
                                                        
 
// @LINE:19
def editUser() = {
   Call("GET", "/users/me/edit")
}
                                                        
 
// @LINE:29
def writeMessage(to:Long) = {
   Call("POST", "/sendPm" + queryString(List(Some(implicitly[QueryStringBindable[Long]].unbind("to", to)))))
}
                                                        
 
// @LINE:15
def me() = {
   Call("GET", "/users/me")
}
                                                        
 
// @LINE:18
def listPm() = {
   Call("GET", "/users/me/messages")
}
                                                        
 
// @LINE:13
def newMessage(name:String) = {
   Call("GET", "/users/" + implicitly[PathBindable[String]].unbind("name", name) + "/messages/new")
}
                                                        
 
// @LINE:6
def index() = {
   Call("GET", "/")
}
                                                        
 
// @LINE:20
def reply(id:Long) = {
   Call("GET", "/users/me/messages/reply/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:21
def showPm(id:Long) = {
   Call("GET", "/users/me/messages/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        

                      
    
}
                            
}
                    


// @LINE:34
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:9
// @LINE:8
// @LINE:7
class ReverseAdmin {
    


 
// @LINE:26
def editUser = JavascriptReverseRoute(
   "controllers.Admin.editUser",
   """
      function(id) {
      return _wA({method:"POST", url:"/admin/users/edit/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:24
def delete = JavascriptReverseRoute(
   "controllers.Admin.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"/admin/users/delete/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:9
def editUserForm = JavascriptReverseRoute(
   "controllers.Admin.editUserForm",
   """
      function(id) {
      return _wA({method:"GET", url:"/admin/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:7
def index = JavascriptReverseRoute(
   "controllers.Admin.index",
   """
      function() {
      return _wA({method:"GET", url:"/admin"})
      }
   """
)
                                                        
 
// @LINE:8
def newUser = JavascriptReverseRoute(
   "controllers.Admin.newUser",
   """
      function() {
      return _wA({method:"GET", url:"/admin/users/new"})
      }
   """
)
                                                        
 
// @LINE:25
def createUser = JavascriptReverseRoute(
   "controllers.Admin.createUser",
   """
      function() {
      return _wA({method:"POST", url:"/admin/users/create"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:28
// @LINE:27
// @LINE:17
// @LINE:16
// @LINE:14
// @LINE:11
// @LINE:10
class ReverseApplication {
    


 
// @LINE:14
def signup = JavascriptReverseRoute(
   "controllers.Application.signup",
   """
      function() {
      return _wA({method:"GET", url:"/users/new"})
      }
   """
)
                                                        
 
// @LINE:16
def user = JavascriptReverseRoute(
   "controllers.Application.user",
   """
      function(name) {
      return _wA({method:"GET", url:"/users/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", name)})
      }
   """
)
                                                        
 
// @LINE:11
def login = JavascriptReverseRoute(
   "controllers.Application.login",
   """
      function() {
      return _wA({method:"GET", url:"/login"})
      }
   """
)
                                                        
 
// @LINE:17
def users = JavascriptReverseRoute(
   "controllers.Application.users",
   """
      function() {
      return _wA({method:"GET", url:"/users"})
      }
   """
)
                                                        
 
// @LINE:10
def index = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"/public"})
      }
   """
)
                                                        
 
// @LINE:28
def newUser = JavascriptReverseRoute(
   "controllers.Application.newUser",
   """
      function() {
      return _wA({method:"POST", url:"/newUser"})
      }
   """
)
                                                        
 
// @LINE:27
def auth = JavascriptReverseRoute(
   "controllers.Application.auth",
   """
      function() {
      return _wA({method:"POST", url:"/auth"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:34
class ReverseAssets {
    


 
// @LINE:34
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:15
// @LINE:13
// @LINE:12
// @LINE:6
class ReversePrivate {
    


 
// @LINE:30
def createKey = JavascriptReverseRoute(
   "controllers.Private.createKey",
   """
      function() {
      return _wA({method:"POST", url:"/createKey"})
      }
   """
)
                                                        
 
// @LINE:31
def saveUser = JavascriptReverseRoute(
   "controllers.Private.saveUser",
   """
      function() {
      return _wA({method:"POST", url:"/saveUser"})
      }
   """
)
                                                        
 
// @LINE:12
def logout = JavascriptReverseRoute(
   "controllers.Private.logout",
   """
      function() {
      return _wA({method:"GET", url:"/logout"})
      }
   """
)
                                                        
 
// @LINE:19
def editUser = JavascriptReverseRoute(
   "controllers.Private.editUser",
   """
      function() {
      return _wA({method:"GET", url:"/users/me/edit"})
      }
   """
)
                                                        
 
// @LINE:29
def writeMessage = JavascriptReverseRoute(
   "controllers.Private.writeMessage",
   """
      function(to) {
      return _wA({method:"POST", url:"/sendPm" + _qS([(""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("to", to)])})
      }
   """
)
                                                        
 
// @LINE:15
def me = JavascriptReverseRoute(
   "controllers.Private.me",
   """
      function() {
      return _wA({method:"GET", url:"/users/me"})
      }
   """
)
                                                        
 
// @LINE:18
def listPm = JavascriptReverseRoute(
   "controllers.Private.listPm",
   """
      function() {
      return _wA({method:"GET", url:"/users/me/messages"})
      }
   """
)
                                                        
 
// @LINE:13
def newMessage = JavascriptReverseRoute(
   "controllers.Private.newMessage",
   """
      function(name) {
      return _wA({method:"GET", url:"/users/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", name) + "/messages/new"})
      }
   """
)
                                                        
 
// @LINE:6
def index = JavascriptReverseRoute(
   "controllers.Private.index",
   """
      function() {
      return _wA({method:"GET", url:"/"})
      }
   """
)
                                                        
 
// @LINE:20
def reply = JavascriptReverseRoute(
   "controllers.Private.reply",
   """
      function(id) {
      return _wA({method:"GET", url:"/users/me/messages/reply/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:21
def showPm = JavascriptReverseRoute(
   "controllers.Private.showPm",
   """
      function(id) {
      return _wA({method:"GET", url:"/users/me/messages/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        

                      
    
}
                            
}
                    


// @LINE:34
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {

// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:9
// @LINE:8
// @LINE:7
class ReverseAdmin {
    


 
// @LINE:26
def editUser(id:Long) = new play.api.mvc.HandlerRef(
   controllers.Admin.editUser(id), HandlerDef(this, "controllers.Admin", "editUser", Seq(classOf[Long]))
)
                              
 
// @LINE:24
def delete(id:Long) = new play.api.mvc.HandlerRef(
   controllers.Admin.delete(id), HandlerDef(this, "controllers.Admin", "delete", Seq(classOf[Long]))
)
                              
 
// @LINE:9
def editUserForm(id:Long) = new play.api.mvc.HandlerRef(
   controllers.Admin.editUserForm(id), HandlerDef(this, "controllers.Admin", "editUserForm", Seq(classOf[Long]))
)
                              
 
// @LINE:7
def index() = new play.api.mvc.HandlerRef(
   controllers.Admin.index(), HandlerDef(this, "controllers.Admin", "index", Seq())
)
                              
 
// @LINE:8
def newUser() = new play.api.mvc.HandlerRef(
   controllers.Admin.newUser(), HandlerDef(this, "controllers.Admin", "newUser", Seq())
)
                              
 
// @LINE:25
def createUser() = new play.api.mvc.HandlerRef(
   controllers.Admin.createUser(), HandlerDef(this, "controllers.Admin", "createUser", Seq())
)
                              

                      
    
}
                            

// @LINE:28
// @LINE:27
// @LINE:17
// @LINE:16
// @LINE:14
// @LINE:11
// @LINE:10
class ReverseApplication {
    


 
// @LINE:14
def signup() = new play.api.mvc.HandlerRef(
   controllers.Application.signup(), HandlerDef(this, "controllers.Application", "signup", Seq())
)
                              
 
// @LINE:16
def user(name:String) = new play.api.mvc.HandlerRef(
   controllers.Application.user(name), HandlerDef(this, "controllers.Application", "user", Seq(classOf[String]))
)
                              
 
// @LINE:11
def login() = new play.api.mvc.HandlerRef(
   controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Seq())
)
                              
 
// @LINE:17
def users() = new play.api.mvc.HandlerRef(
   controllers.Application.users(), HandlerDef(this, "controllers.Application", "users", Seq())
)
                              
 
// @LINE:10
def index() = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq())
)
                              
 
// @LINE:28
def newUser() = new play.api.mvc.HandlerRef(
   controllers.Application.newUser(), HandlerDef(this, "controllers.Application", "newUser", Seq())
)
                              
 
// @LINE:27
def auth() = new play.api.mvc.HandlerRef(
   controllers.Application.auth(), HandlerDef(this, "controllers.Application", "auth", Seq())
)
                              

                      
    
}
                            

// @LINE:34
class ReverseAssets {
    


 
// @LINE:34
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            

// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:15
// @LINE:13
// @LINE:12
// @LINE:6
class ReversePrivate {
    


 
// @LINE:30
def createKey() = new play.api.mvc.HandlerRef(
   controllers.Private.createKey(), HandlerDef(this, "controllers.Private", "createKey", Seq())
)
                              
 
// @LINE:31
def saveUser() = new play.api.mvc.HandlerRef(
   controllers.Private.saveUser(), HandlerDef(this, "controllers.Private", "saveUser", Seq())
)
                              
 
// @LINE:12
def logout() = new play.api.mvc.HandlerRef(
   controllers.Private.logout(), HandlerDef(this, "controllers.Private", "logout", Seq())
)
                              
 
// @LINE:19
def editUser() = new play.api.mvc.HandlerRef(
   controllers.Private.editUser(), HandlerDef(this, "controllers.Private", "editUser", Seq())
)
                              
 
// @LINE:29
def writeMessage(to:Long) = new play.api.mvc.HandlerRef(
   controllers.Private.writeMessage(to), HandlerDef(this, "controllers.Private", "writeMessage", Seq(classOf[Long]))
)
                              
 
// @LINE:15
def me() = new play.api.mvc.HandlerRef(
   controllers.Private.me(), HandlerDef(this, "controllers.Private", "me", Seq())
)
                              
 
// @LINE:18
def listPm() = new play.api.mvc.HandlerRef(
   controllers.Private.listPm(), HandlerDef(this, "controllers.Private", "listPm", Seq())
)
                              
 
// @LINE:13
def newMessage(name:String) = new play.api.mvc.HandlerRef(
   controllers.Private.newMessage(name), HandlerDef(this, "controllers.Private", "newMessage", Seq(classOf[String]))
)
                              
 
// @LINE:6
def index() = new play.api.mvc.HandlerRef(
   controllers.Private.index(), HandlerDef(this, "controllers.Private", "index", Seq())
)
                              
 
// @LINE:20
def reply(id:Long) = new play.api.mvc.HandlerRef(
   controllers.Private.reply(id), HandlerDef(this, "controllers.Private", "reply", Seq(classOf[Long]))
)
                              
 
// @LINE:21
def showPm(id:Long) = new play.api.mvc.HandlerRef(
   controllers.Private.showPm(id), HandlerDef(this, "controllers.Private", "showPm", Seq(classOf[Long]))
)
                              

                      
    
}
                            
}
                    
                