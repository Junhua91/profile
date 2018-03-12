package com.junhua.profile.controllers

import javax.inject.Inject

import com.junhua.profile.model.LoginModel.{UserInfo, identifierForm}
import play.api.cache.{AsyncCacheApi}
import play.api.mvc._

import scala.concurrent.Await
import scala.concurrent.duration._



/**
  * Summary: in this controller, we can see:
  *   1. the use of Form
  *   2. the use of cookie
  *   3. the use of session
  *
  * Note:
  *   1. session in play framework almost the same with cookie. The data is not stored by server but are added to each subsequent
  *       HTTP request. If we want to cache some data related to session, we can use play cache and store a unique ID in the session
  *   2. cookies and session can share between multiple HTTP.
  *   3. domain in cookie is the url (not the uri) we want to store the cookie. example.com => www.example.com
  * @param cc
  */
class LoginController @Inject() (cc: ControllerComponents,cache:AsyncCacheApi)extends AbstractController(cc){

  def login = Action { implicit request =>


    //read cookie
//    if(request.cookies.get("username").nonEmpty)
//      Ok("not the first time -- cookie")
//    else
//      Ok(views.html.login.login(vm))

//    //read session
//    if(request.session.get("username").nonEmpty)
//      Ok("not the first time -- session")
//    else
      Ok(views.html.login.login.render())



  }

  def checkCookies = Action { implicit request =>
    identifierForm.bindFromRequest().fold(
      formWithErrors => {
        BadRequest("error")
      },
      userData=>{
        val username  = userData.identifier
        val password = userData.password

        //add cookies
        val c1 = new Cookie("username",username)
        val c2 = new Cookie("password",password)
        Ok("success").withCookies(c1).withCookies(c2)
      }
    )
  }

  def checkSessions = Action {implicit request =>

    identifierForm.bindFromRequest().fold(
      formWithErrors => {
        BadRequest("error")
      },
      userData=>{
        val username  = userData.identifier
        val password = userData.password
        //add session
        Ok("success").
          withSession(request.session + ("username" -> username) + ("password"->password))
      }
    )
  }

  def checkCache = Action{implicit request=>

    if(request.cookies.get("SessionID").nonEmpty){
      val sessionId = request.cookies.get("SessionID").get.value
      val userInfo = Await.result(cache.get[UserInfo](sessionId),10 seconds)
      Ok("successfully login" + userInfo.get.identifier)
    }else {
      identifierForm.bindFromRequest().fold(
        formWithErrors => {
          BadRequest("error")
        },
        userData=>{
          val username  = userData.identifier
          val password = userData.password
          val userInfo= UserInfo(
            identifier = username,
            password = password
          )
          //add cache
          cache.set("12345",userInfo)
          //add session
          Ok("cache success").withCookies(new Cookie("SessionID","12345"))
        }
      )
    }
  }
}
