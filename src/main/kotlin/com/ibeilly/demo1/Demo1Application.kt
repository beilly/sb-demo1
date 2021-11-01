package com.ibeilly.demo1

import com.ibeilly.demo1.controler.TestController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Demo1Application

fun main(args: Array<String>) {
    runApplication<Demo1Application>(*args)
}

val LOG: Logger = LoggerFactory.getLogger(Demo1Application::class.java)
