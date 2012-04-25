package controllers

import play.api.data._
import play.api.data.Forms._
import models.User
import play.api.libs.Crypto
import play.api.i18n.Messages


object Forms {
  val loginForm = Form (
    tuple (
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )
    verifying(Messages("invalid_user_name_or_password"), value => !models.User.connect(value._1, Crypto.sign(value._2)).isEmpty)
  )
  
  val signUpForm = Form (
    tuple (
      "username" -> nonEmptyText(minLength = 3).verifying(Messages("username_is_taken"), username => models.User.findBy("username" -> username).isEmpty),
      "password" -> tuple (
        "main" -> nonEmptyText,
        "confirm" -> nonEmptyText
      ).verifying ( Messages("passwords_dont_match"), passwords =>
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

  val messageFormEx = Form (
    tuple (
      "receiver" -> nonEmptyText.verifying("User not found", value => !models.User.findBy("username" -> value).isEmpty),
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
          Crypto.sign(oldPw) == user.hashedPW
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

  val newUserForm = Form {
    tuple(
      "user" -> tuple (
        "username" -> nonEmptyText.verifying("Username is allready taken", value => models.User.findBy("username" -> value).isEmpty),
        "password" -> nonEmptyText
      ),
      "bday" -> date,
      "dday" -> date,
      "about" -> text,
      "anonym" -> boolean,
      "admin" -> boolean
    )
  }

  val adminEditUserForm = Form {
    tuple(
      "bday" -> date,
      "dday" -> date,
      "about" -> text,
      "anonym" -> boolean,
      "admin" -> boolean
    )
  }
}

