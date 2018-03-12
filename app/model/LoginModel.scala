package com.junhua.profile.model

import play.api.data._
import play.api.data.Forms._

object LoginModel {

  case class UserInfo(
    identifier: String,
    password:String
  )

  val identifierForm = Form(
    mapping(
      "identifier"->nonEmptyText,
      "password"->nonEmptyText
    )(UserInfo.apply)(UserInfo.unapply)
  )

  case class LoginView(
    username:String,
    password:String
  )
}
