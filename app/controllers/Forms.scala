package controllers

import play.api.data._
import play.api.data.Forms._
import java.util.Date
import models.User


object Forms {
  val loginForm = Form (
    tuple (
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )
    verifying("Invalid user name or password", value => !models.User.connect(value._1, value._2).isEmpty)
  )
  
  val signUpForm = Form (
    tuple (
      "username" -> nonEmptyText(minLength = 3).verifying("Username is taken!", username => models.User.findBy("username" -> username).isEmpty),
      "password" -> tuple (
        "main" -> nonEmptyText,
        "confirm" -> nonEmptyText
      ).verifying ( "Passwords don't match!", passwords =>
        passwords._1 == passwords._2
      ),
      "registrationkey" -> nonEmptyText.verifying("Invalid registration key!", {key =>
        val keys = models.InvitationKey.findByKey(key)
        if(keys.isEmpty)
          false
        else {
          true
        }
      })
    )
  )
  
  val messageForm = Form (
    tuple (
      "title" -> text,
      "content" -> nonEmptyText
    )
  )

  val editUserForm = Form {
    tuple(
      "user" -> tuple (
        "username" -> nonEmptyText,
        "new" -> text,
        "old" -> text
      ).verifying("Old password is missing", value =>{
        println(value)
        if(!value._2.isEmpty) {
          val oldPw = value._3
          val user = User.findBy("username" -> value._1).head
          oldPw == user.hashedPW
        } else {
          true
        }
      }),
      "bday" -> date,
      "dday" -> date,
      "about" -> text,
      "anonym" -> boolean,
      "admin" -> boolean
    )
  }
}

