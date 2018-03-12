package com.junhua.profile.services

import org.springframework.stereotype.Service
import play.api.mvc.RequestHeader

@Service
class LoginService {

  def isLogin(request:RequestHeader):Boolean = {
    request.cookies.get("SessionID").nonEmpty
  }
}
