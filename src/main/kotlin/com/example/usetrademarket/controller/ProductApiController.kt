package com.example.usetrademarket.controller

import com.example.usetrademarket.common.ApiResponse
import com.example.usetrademarket.common.UseTradeException
import com.example.usetrademarket.domain.product.ProductService
import com.example.usetrademarket.domain.product.entity.Product
import com.example.usetrademarket.domain.product.registration.ProductImageService
import com.example.usetrademarket.domain.product.registration.ProductRegistrationService
import com.example.usetrademarket.domain.product.registration.dto.ProductRegistrationRequest
import com.example.usetrademarket.domain.product.toProductListItemResponse
import com.example.usetrademarket.domain.toProductResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1")
class ProductApiController @Autowired constructor(
        private val productImageService: ProductImageService,
        private val productRegistrationService: ProductRegistrationService,
        private val productService: ProductService
) {

    @PostMapping("/product_images")
    fun uploadImage(image: MultipartFile) = ApiResponse.ok(
            productImageService.uploadImage(image)
    )

    @PostMapping("/products")
    fun register(
            @RequestBody request: ProductRegistrationRequest
    ) = ApiResponse.ok(
            productRegistrationService.register(request)
    )

    @GetMapping("/products")
    fun search(
            @RequestParam productId: Long,
            @RequestParam(required = false) categoryId : Int?,
            @RequestParam direction : String,
            @RequestParam(required = false) keyword : String?,
            @RequestParam(required = false) limit: Int?
    ) = productService
            .search(categoryId, productId, direction, keyword,limit ?: 10)
            .mapNotNull(Product::toProductListItemResponse)
            .let { ApiResponse.ok(it) }

    @GetMapping("/products/{id}")
    fun get(@PathVariable id : Long) = productService.get(id)?.let {
        ApiResponse.ok(it.toProductResponse())
    } ?: throw UseTradeException("상품 정보를 찾을 수 없습니다.")

}