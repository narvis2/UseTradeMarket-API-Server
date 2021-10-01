package com.example.usetrademarket.domain

import com.example.usetrademarket.common.UseTradeException
import com.example.usetrademarket.domain.product.entity.Product

data class ProductResponse(
        val id : Long,
        val name : String,
        val description : String,
        val price : Int,
        val status : String,
        val sellerId : Long,
        val imagePaths : List<String>
)

fun Product.toProductResponse() = id?.let {
    ProductResponse(
            it,
            name,
            description,
            price,
            status.name,
            userId,
            images.map {
                it.path
            }
    )
} ?: throw UseTradeException("상품 정보를 찾을 수 없습니다.")