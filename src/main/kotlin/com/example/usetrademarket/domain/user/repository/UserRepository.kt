package com.example.usetrademarket.domain.user.repository

import com.example.usetrademarket.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String) : User?
}