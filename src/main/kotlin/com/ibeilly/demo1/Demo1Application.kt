package com.ibeilly.demo1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
class Demo1Application

fun main(args: Array<String>) {
    runApplication<Demo1Application>(*args)
}
