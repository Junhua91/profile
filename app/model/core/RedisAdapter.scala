package com.junhua.profile.model.core

import org.springframework.beans.factory.annotation.{Autowired, Qualifier}
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisAdapter {

  @Autowired
  @Qualifier("redisTemplate")
  val main:RedisTemplate[String,String] = null
}
