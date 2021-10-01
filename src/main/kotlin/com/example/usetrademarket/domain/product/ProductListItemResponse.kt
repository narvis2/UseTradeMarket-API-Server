package com.example.usetrademarket.domain.product

import com.example.usetrademarket.domain.product.entity.Product
import com.example.usetrademarket.domain.product.entity.ProductImage

data class ProductListItemResponse(
        val id : Long,
        val name : String,
        val description : String,
        val price : Int,
        val status : String,
        val sellerId : Long,
        val imagePaths : List<String>,
        val sellerName: String
)

fun Product.toProductListItemResponse() = id?.let {
    ProductListItemResponse(
            it,
            name,
            description,
            price,
            status.name,
            userId,
            images.map { it.toThumbs() },
            sellerName
    )
}

fun ProductImage.toThumbs(): String {
    val ext = path.takeLastWhile { it != '.' }
    val fileName = path.takeWhile { it != '.' }
    val thumbnailPath = "$fileName-thumb.$ext"
    return if (ext == "jpg") thumbnailPath else "$thumbnailPath.jpg"
}