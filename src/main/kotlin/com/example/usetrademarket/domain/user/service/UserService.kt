package com.example.usetrademarket.domain.user.service

import com.example.usetrademarket.domain.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
        private val userRepository: UserRepository
) {
    // 토큰이 변경되었을 때 로그인 사용자의 토큰을 업데이트
    fun updateFcmToken(userId: Long, fcmToken: String) =
            userRepository.findByIdOrNull(userId)?.run {
                this.fcmToken = fcmToken
                userRepository.save(this)
            } ?: throw IllegalStateException("사용자 정보 없음")

    fun find(userId: Long) = userRepository.findByIdOrNull(userId)
}