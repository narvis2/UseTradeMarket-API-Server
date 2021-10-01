package com.example.usetrademarket.domain.auth.signin.dto

data class SigninResponse(
        val token: String,
        val refreshToken: String,
        val userName: String,
        val userId: Long,
        val email: String
)