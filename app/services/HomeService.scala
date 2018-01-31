package com.junhua.profile.services

import com.junhua.profile.Dao.MainPageDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.junhua.profile.services.HomeModels.{HomeControllerModel, HomePageModel, HomeViewModel}
import model.db.Main._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

@Service
class HomeService {

  @Autowired
  val homeDao:MainPageDao = null

  def loadData:Future[HomeControllerModel]={
    val homeDataFuture:Future[Seq[TbMainPageRow]] = homeDao.getData
    for {
      homeData <- homeDataFuture
    }yield {
      val datas = homeData.map{data=>
        HomePageModel(
          name = data.name,
          age = data.age,
          address = data.address.getOrElse("")
        )
      }
      HomeControllerModel(
        homeDatas = datas
      )
    }
  }

  def prepareView(cm:HomeControllerModel):HomeViewModel={
    HomeViewModel(
      homeView = cm.homeDatas
    )
  }


}

object HomeModels{

  case class HomePageModel(
    name:String,
    age:Int,
    address:String
  )

  case class HomeControllerModel(
    homeDatas:Seq[HomePageModel]
  )

  case class HomeViewModel(
    homeView:Seq[HomePageModel]
  )
}
