package com.junhua.profile.services

import com.junhua.profile.Dao.MainPageDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.junhua.profile.services.HomeModels.{HomeControllerModel, HomePageModel, HomeViewModel}
import com.junhua.profile.model.core.Core
import model.db.Main._
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Service
class HomeService {

  @Autowired
  val homeDao:MainPageDao = null
  @Autowired
  val core:Core = null

  val logger = LoggerFactory.getLogger(this.getClass)

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

    core.redisAdapter.main.opsForValue().set("junhua","love juan")
    logger.info("home service")
    logger.info(core.redisAdapter.main.opsForValue().get("junhua"))
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
