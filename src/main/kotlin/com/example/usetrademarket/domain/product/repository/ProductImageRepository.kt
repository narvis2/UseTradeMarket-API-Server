package com.example.usetrademarket.domain.product.repository

import com.example.usetrademarket.domain.product.entity.ProductImage
import org.springframework.data.jpa.repository.JpaRepository

interface ProductImageRepository : JpaRepository<ProductImage, Long> {

    fun findByIdIn(imageIds: List<Long>) : MutableList<ProductImage>

}