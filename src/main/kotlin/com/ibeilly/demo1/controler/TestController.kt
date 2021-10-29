package com.ibeilly.demo1.controler

import com.ibeilly.demo1.interceptor.HandlerInterceptor1
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController {
    @GetMapping("/test")
    fun test(): String {
        LOG.info("test: ${System.currentTimeMillis()}")
        return "test"
    }

    @GetMapping("/test2")
    fun test2(): String {
        return "test2"
    }

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(TestController::class.java)
    }
}