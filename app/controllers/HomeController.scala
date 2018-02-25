package com.junhua.profile.controllers

import java.text.DateFormat
import java.util.{Calendar, Date, Locale, TimeZone}
import javax.inject._

import play.api.mvc._
import com.junhua.profile.services.HomeService
import com.junhua.profile.util.TimeUtil

import scala.concurrent.ExecutionContext.Implicits.global

class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  @Inject
  var homeService:HomeService = null


  def index = Action.async {
    for{
      cm <- homeService.loadData
    }yield{
      val vm = homeService.prepareView(cm)
      Ok(views.html.explore.home(vm))
    }
  }

}
