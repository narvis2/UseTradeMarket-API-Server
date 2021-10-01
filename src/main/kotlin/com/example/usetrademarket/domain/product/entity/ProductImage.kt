package com.example.usetrademarket.domain.product.entity

import com.example.usetrademarket.domain.jpa.BaseEntity
import java.util.*
import javax.persistence.*

@Entity(name = "product_entity")
class ProductImage(
        var path: String,
        var productId: Long? = null
) : BaseEntity() {
}
