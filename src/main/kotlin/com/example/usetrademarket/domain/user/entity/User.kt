package com.example.usetrademarket.domain.user.entity

import com.example.usetrademarket.domain.jpa.BaseEntity
import javax.persistence.*

@Entity(name = "user")
class User(
        var email: String,
        var password: String,
        var name: String,
        var fcmToken: String?
) : BaseEntity()