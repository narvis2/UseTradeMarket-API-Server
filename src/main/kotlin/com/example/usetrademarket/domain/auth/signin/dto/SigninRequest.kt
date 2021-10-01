package com.example.usetrademarket.domain.auth.signin.dto

data class SigninRequest(
        val email: String,
        val password: String,
        val fcmToken: String?
)