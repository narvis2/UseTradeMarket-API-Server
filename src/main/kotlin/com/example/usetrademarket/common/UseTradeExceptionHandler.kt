package com.example.usetrademarket.common

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@ControllerAdvice
@RestController
class UseTradeExceptionHandler {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(UseTradeException::class)
    fun handleUseTradeException(e: UseTradeException) : ApiResponse {
        logger.error("API error", e)
        return ApiResponse.error(e.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e : Exception) : ApiResponse {
        logger.error("API error", e)
        return ApiResponse.error("알 수 없는 오류가 발생하였습니다.")
    }
}