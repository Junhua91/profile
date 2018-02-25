package com.junhua.profile.model.core

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class Core {

  @Autowired
  val redisAdapter:RedisAdapter = null
  @Autowired
  val dbAdapter:DbAdapter = null

}
