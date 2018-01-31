package com.junhua.profile.Dao

import com.junhua.profile.model.core.DbAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import scala.concurrent.Future
import slick.jdbc.MySQLProfile.api._

@Service
class MainPageDao {

  @Autowired
  val dbAdapter:DbAdapter = null

  import model.db.Main._
  val mainPage = TbMainPage
  lazy val db = dbAdapter.profileDb

  def getData:Future[Seq[TbMainPageRow]] = {
    db.run(mainPage.result)
  }

}
