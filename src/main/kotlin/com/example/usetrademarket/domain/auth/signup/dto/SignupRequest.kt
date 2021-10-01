package com.example.usetrademarket.domain.auth.signup.dto

data class SignupRequest(
        val email: String,
        val name: String,
        val password: String,
        val fcmToken: String?
)