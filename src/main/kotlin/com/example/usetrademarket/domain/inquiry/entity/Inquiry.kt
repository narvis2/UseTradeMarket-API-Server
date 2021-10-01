package com.example.usetrademarket.domain.inquiry.entity

import com.example.usetrademarket.domain.jpa.BaseEntity
import com.example.usetrademarket.domain.product.entity.Product
import com.example.usetrademarket.domain.user.entity.User
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Inquiry(
        val productId: Long,
        val requestUserId: Long,
        val productOwnerId : Long,
        val question : String,
        var answer : String? = null
) : BaseEntity() {

    @ManyToOne
    @JoinColumn(
            name = "requestUserId",
            insertable = false,
            updatable = false
    )
    var requestUser: User? = null

    @ManyToOne
    @JoinColumn(
            name = "productOwnerId",
            insertable = false,
            updatable = false
    )
    var productOwner : User? = null

    @ManyToOne
    @JoinColumn(
            name = "productId",
            insertable = false,
            updatable = false
    )
    var product : Product? = null
}
