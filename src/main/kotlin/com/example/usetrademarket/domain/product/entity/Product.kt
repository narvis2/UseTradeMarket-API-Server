package com.example.usetrademarket.domain.product.entity

import com.example.usetrademarket.domain.jpa.BaseEntity
import com.example.usetrademarket.domain.product.ProductStatus
import java.util.*
import javax.persistence.*

@Entity(name = "product")
class Product(
        @Column(length = 40)
        var name: String,
        @Column(length = 500)
        var description: String,
        var price: Int,
        var categoryId: Int,
        @Enumerated(EnumType.STRING)
        var status: ProductStatus,
        @OneToMany
        @JoinColumn(name = "productId")
        var images: MutableList<ProductImage>,
        val userId: Long,
        val sellerName: String
) : BaseEntity()