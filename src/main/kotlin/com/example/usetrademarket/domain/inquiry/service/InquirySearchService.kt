package com.example.usetrademarket.domain.inquiry.service

import com.example.usetrademarket.domain.inquiry.entity.Inquiry
import com.example.usetrademarket.domain.inquiry.repository.InquiryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class InquirySearchService @Autowired constructor(
        private val inquiryRepository: InquiryRepository
) {

    fun getProductInquires(
            inquiryId: Long,
            productId: Long?,
            requestUserId: Long?,
            productOwnerId: Long?,
            direction: String
    ): List<Inquiry> {
        val condition = InquirySearchCondition(
                direction,
                productId != null,
                requestUserId != null,
                productOwnerId != null
        )
        println("===================================================")
        println("condition -> $condition")
        println("===================================================")
        return when (condition) {
            NEXT_FOR_PRODUCT -> {
                inquiryRepository.findByProductIdAndIdLessThanOrderByIdDesc(
                        productId,
                        inquiryId,
                        PageRequest.of(0, 10)
                )
            }
            PREV_FOR_PRODUCT -> {
                inquiryRepository.findByProductIdAndIdGreaterThanOrderByIdDesc(
                        productId,
                        inquiryId,
                        PageRequest.of(0, 10)
                )
            }
            NEXT_FOR_USER -> {
                inquiryRepository.findByRequestUserIdAndIdLessThanOrderByIdDesc(
                        requestUserId,
                        inquiryId,
                        PageRequest.of(0, 10)
                )
            }
            PREV_FOR_USER -> {
                inquiryRepository.findByRequestUserIdAndIdGreaterThanOrderByIdDesc(
                        requestUserId,
                        inquiryId,
                        PageRequest.of(0, 10)
                )
            }
            NEXT_FOR_USER_PRODUCT -> {
                inquiryRepository.findByProductOwnerIdAndIdLessThanOrderByIdDesc(
                        productOwnerId,
                        inquiryId,
                        PageRequest.of(0, 10)
                )
            }
            PREV_FOR_USER_PRODUCT -> {
                inquiryRepository.findByProductOwnerIdAndIdGreaterThanOrderByIdDesc(
                        productOwnerId,
                        inquiryId,
                        PageRequest.of(0, 10)
                )
            }
            else -> throw IllegalArgumentException("?????? ?????? ?????? ??????")
        }
    }

    data class InquirySearchCondition(
            val direction: String,
            val hasProductId: Boolean = false,
            val hasRequestUserId: Boolean = false,
            val hasProductOwnerId: Boolean = false
    )

    companion object {
        val NEXT_FOR_PRODUCT = InquirySearchCondition("next", hasProductId = true)
        val PREV_FOR_PRODUCT = InquirySearchCondition("prev", hasProductId = true)
        val NEXT_FOR_USER = InquirySearchCondition("next", hasRequestUserId = true)
        val PREV_FOR_USER = InquirySearchCondition("prev", hasRequestUserId = true)
        val NEXT_FOR_USER_PRODUCT = InquirySearchCondition("next", hasProductOwnerId = true)
        val PREV_FOR_USER_PRODUCT = InquirySearchCondition("prev", hasProductOwnerId = true)
    }
}