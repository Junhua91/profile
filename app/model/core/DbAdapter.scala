package com.junhua.profile.model.core

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

import org.springframework.stereotype.Service
import slick.jdbc.JdbcBackend._

@Service
class DbAdapter {

  @Autowired
  @Qualifier("profileDataSource")
  val profileSource:DataSource = null

  lazy val profileDb = Database.forDataSource(profileSource,Some(100))

}
