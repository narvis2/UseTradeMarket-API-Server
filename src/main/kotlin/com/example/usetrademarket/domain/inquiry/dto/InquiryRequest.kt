package com.example.usetrademarket.domain.inquiry.dto

import com.example.usetrademarket.domain.inquiry.InquiryType

data class InquiryRequest(
        val type: InquiryType,
        val inquiryId: Long?,
        val productId: Long,
        val content: String
)
