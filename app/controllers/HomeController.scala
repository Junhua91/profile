package com.junhua.profile.controllers

import javax.inject._

import play.api.mvc._
import com.junhua.profile.services.{HomeService, LoginService}

import scala.concurrent.ExecutionContext.Implicits.global

class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  @Inject
  var homeService: HomeService = null
  @Inject
  var loginService: LoginService = null


  def index = Action.async { implicit request =>
    for {
      cm <- homeService.loadData
    } yield {
      val vm = homeService.prepareView(cm)
      if (loginService.isLogin(request)) Ok("already login")
      else Ok(views.html.explore.home(vm))
    }
  }

}
