package com.example.usetrademarket.controller

import com.example.usetrademarket.common.ApiResponse
import com.example.usetrademarket.domain.auth.UserContextHolder
import com.example.usetrademarket.domain.auth.signup.dto.SignupRequest
import com.example.usetrademarket.domain.auth.signup.SignupService
import com.example.usetrademarket.domain.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1")
class UserApiController @Autowired constructor(
        private val signupService: SignupService,
        private val userService: UserService,
        private val userContextHolder: UserContextHolder
) {

    @GetMapping("/hello")
    fun intro() = ApiResponse.ok("UseTradeApp")

    @PostMapping("/users")
    fun signup(@RequestBody signupRequest: SignupRequest) =
            ApiResponse.ok(signupService.signup(signupRequest))

    @PutMapping("/users/fcm-token")
    fun updateFcmToken(
            @RequestBody fcmToken : String
    ) = userContextHolder.id?.let { userId ->
        ApiResponse.ok(userService.updateFcmToken(userId, fcmToken))
    } ?: ApiResponse.error("토큰 갱신 실패")

    
}