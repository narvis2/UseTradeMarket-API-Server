package com.example.usetrademarket.controller

import com.example.usetrademarket.common.ApiResponse
import com.example.usetrademarket.common.UseTradeException
import com.example.usetrademarket.domain.auth.UserContextHolder
import com.example.usetrademarket.domain.inquiry.dto.InquiryRequest
import com.example.usetrademarket.domain.inquiry.dto.toInquiryResponse
import com.example.usetrademarket.domain.inquiry.entity.Inquiry
import com.example.usetrademarket.domain.inquiry.service.InquirySearchService
import com.example.usetrademarket.domain.inquiry.service.InquiryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class InquiryApiController @Autowired constructor(
        private val inquiryService: InquiryService,
        private val inquirySearchService: InquirySearchService,
        private val userContextHolder: UserContextHolder
) {

    @PostMapping("/inquiries")
    fun register(
            @RequestBody inquiryRequest: InquiryRequest
    ) = userContextHolder.id?.let { userId ->
        ApiResponse.ok(inquiryService.register(inquiryRequest, userId))
    } ?: throw UseTradeException("상품 문의에 필요한 사용자 정보가 없습니다.")

    @GetMapping("/inquiries")
    fun getProductInquiries(
            @RequestParam inquiryId : Long,
            @RequestParam(required = false) productId : Long?,
            @RequestParam(required = false) requestUserId : Long?,
            @RequestParam(required = false) productOwnerId: Long?,
            @RequestParam direction: String
    ): ApiResponse {
        println("-----------------------------------------------------")
        println("inquiryId -> $inquiryId")
        println("productId -> $productId")
        println("requestUserId -> $requestUserId")
        println("productOwnerId -> $productOwnerId")
        println("direction -> $direction")
        val result = inquirySearchService.getProductInquires(
                inquiryId,
                productId,
                if (requestUserId == null) null else userContextHolder.id,
                if (productOwnerId == null) null else userContextHolder.id,
                direction
        )
        println("List<Inquiry> -> ${result.toString()}")
        println("-----------------------------------------------------")
        return inquirySearchService.getProductInquires(
                inquiryId,
                productId,
                if (requestUserId == null) null else userContextHolder.id,
                if (productOwnerId == null) null else userContextHolder.id,
                direction
        ).mapNotNull(Inquiry::toInquiryResponse)
                .let {
                    ApiResponse.ok(it)
                }
    }
}