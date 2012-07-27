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
      "username" -> nonEmptyText(minLength = 3).verifying(Messages("username_is_taken"), username => models.User.findOneByName(username).isEmpty),
      "password" -> tuple (
        "main" -> nonEmptyText,
        "confirm" -> nonEmptyText
      ).verifying ( Messages("passwords_do_not_match"), passwords =>
        passwords._1 == passwords._2
      ),
      "registrationkey" -> nonEmptyText.verifying(Messages("invalid_registration_key"), {key =>
        val keys = models.User.findAllBy("keys" -> key)
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
      "receiver" -> nonEmptyText.verifying(Messages("user_not_found"), value => !models.User.findOneByName(value).isEmpty),
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
      ).verifying(Messages("old_password_is_missing"), value =>{
        if(!value._2.isEmpty) {
          val oldPw = value._3
          val user = User.findOneByName(value._1).get
          Crypto.sign(oldPw) == user.hashedPassword
        } else {
          true
        }
      }),
      "gender" -> nonEmptyText,
      "bday" -> date,
      "about" -> text,
      "anonym" -> boolean,
      "admin" -> boolean
    )
  }

  val newUserForm = Form {
    tuple(
      "user" -> tuple (
        "username" -> nonEmptyText.verifying(Messages("username_is_taken"), value => models.User.findOneByName(value).isEmpty),
        "password" -> nonEmptyText
      ),
      "bday" -> date,
      "about" -> text,
      "anonym" -> boolean,
      "admin" -> boolean
    )
  }

  val adminEditUserForm = Form {
    tuple(
      "bday" -> date,
      "about" -> text,
      "anonym" -> boolean,
      "admin" -> boolean
    )
  }
}

