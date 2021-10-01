package com.example.usetrademarket.domain.product.registration.dto

data class ProductRegistrationRequest(
        val name: String,
        val description : String,
        val price: Int,
        val categoryId : Int,
        val imageIds : List<Long?>,
        val sellerName : String
)