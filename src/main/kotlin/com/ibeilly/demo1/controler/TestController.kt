package com.ibeilly.demo1.controler

import com.ibeilly.demo1.model.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController {
    @GetMapping("/test")
    fun test(): String {
        LOG.info("test: ${System.currentTimeMillis()}")
        return "test"
    }

    @PostMapping("/test1")
    fun test1(): Map<String, Any> {
        LOG.info("test1: ${System.currentTimeMillis()}")
        return mapOf<String, Any>(
            "msg" to "hello"
        )
    }

    @PostMapping("/test2")
    fun test2(): Message<Any> {
        return Message.fail(mapOf<String, Any>(
            "msg" to "hello"
        ))
    }

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(TestController::class.java)
    }
}