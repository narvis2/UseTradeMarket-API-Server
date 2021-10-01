package com.example.usetrademarket.controller

import com.example.usetrademarket.common.ApiResponse
import com.example.usetrademarket.domain.auth.JWTUtil
import com.example.usetrademarket.domain.auth.UserContextHolder
import com.example.usetrademarket.domain.auth.signin.SigninService
import com.example.usetrademarket.domain.auth.signin.dto.SigninRequest
import com.example.usetrademarket.interceptor.TokenValidationInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class SigninApiController @Autowired constructor(
        private val signinService: SigninService,
        private val userContextHolder: UserContextHolder
) {

    @PostMapping("/signin")
    fun signin(@RequestBody signinRequest: SigninRequest) : ApiResponse {
        return ApiResponse.ok(signinService.signin(signinRequest))
    }

    // token 재발급 API
    @PostMapping("/refresh_token")
    fun refreshToken(
            @RequestParam("grant_type") grantType: String
    ) : ApiResponse {
        if (grantType != TokenValidationInterceptor.GRANT_TYPE_REFRESH) {
            throw IllegalArgumentException("grant_type 없음")
        }

        return userContextHolder.email?.let {
            ApiResponse.ok(JWTUtil.createToken(it))
        } ?: throw IllegalArgumentException("사용자 정보 없음")
    }

}