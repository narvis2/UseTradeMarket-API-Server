package com.example.usetrademarket.domain.product.repository

import com.example.usetrademarket.domain.product.entity.Product
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductRepository : JpaRepository<Product, Long> {

    // 상품 정보가 위쪽으로 스크롤될 때 호출되는 함수 -> Id 값이 보다 큰 상품들을 내림차순으로 읽어옴
    fun findByCategoryIdAndIdGreaterThanOrderByIdDesc(
            categoryId: Int?,
            id: Long,
            pageable: Pageable
    ) : List<Product>

    // 상품 리스트가 아래쪽으로 스크롤될 때 호출되는 함수 -> Id 값이 보다 작은 상품들을 내림차운으로 읽어옴
    fun findByCategoryIdAndIdLessThanOrderByIdDesc(
            categoryId: Int?,
            id: Long ,
            pageable: Pageable
    ) : List<Product>

    // 상품명에 키워드가 포함되어 있는 상품을 검색하는 함수
    fun findByIdGreaterThanAndNameLikeOrderByIdDesc(
            id: Long,
            keyword: String,
            pageable: Pageable
    ) : List<Product>

    fun findByIdLessThanAndNameLikeOrderByIdDesc(
            id: Long,
            keyword: String,
            pageable: Pageable
    ) : List<Product>

}