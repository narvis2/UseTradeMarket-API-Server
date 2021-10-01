package com.example.usetrademarket.domain.auth.signin

import com.example.usetrademarket.common.UseTradeException
import com.example.usetrademarket.domain.auth.JWTUtil
import com.example.usetrademarket.domain.auth.signin.dto.SigninRequest
import com.example.usetrademarket.domain.auth.signin.dto.SigninResponse
import com.example.usetrademarket.domain.user.entity.User
import com.example.usetrademarket.domain.user.repository.UserRepository
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SigninService @Autowired constructor(
        private val userRepository: UserRepository
) {

    fun signin(signinRequest: SigninRequest) : SigninResponse {
        val user = userRepository.findByEmail(signinRequest.email.lowercase(Locale.getDefault()))
                ?: throw UseTradeException("로그인 정보를 확인해주세요.")

        if (isNotValidPassword(signinRequest.password, user.password)) {
            throw UseTradeException("로그인 정보를 확인해주세요.")
        }

        user.fcmToken = signinRequest.fcmToken
        userRepository.save(user)

        return responseWithTokens(user)
    }

    private fun isNotValidPassword(plain: String, hashed: String) =
            BCrypt.checkpw(plain, hashed).not()

    private fun responseWithTokens(user: User) = user.id?.let { userId ->
        SigninResponse(
                JWTUtil.createToken(user.email),
                JWTUtil.createRefreshToken(user.email),
                user.name,
                userId,
                user.email
        )
    } ?: throw IllegalStateException("user.id 없음.")
}