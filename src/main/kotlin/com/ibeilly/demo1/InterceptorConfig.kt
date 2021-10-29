package com.ibeilly.demo1

import com.ibeilly.demo1.interceptor.HandlerInterceptor1
import com.ibeilly.demo1.interceptor.HandlerInterceptor2
import com.ibeilly.demo1.interceptor.HandlerInterceptor3
import lombok.AllArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@AllArgsConstructor
class InterceptorConfig: WebMvcConfigurer {
    private val myInterceptor: HandlerInterceptor1 = HandlerInterceptor1()

    private val myInterceptor2: HandlerInterceptor2 = HandlerInterceptor2()

    private val myInterceptor3: HandlerInterceptor3 = HandlerInterceptor3()

    override fun addInterceptors(registry: InterceptorRegistry) {
        LOG.info("addInterceptors")
        registry.addInterceptor(myInterceptor).addPathPatterns("/**")
        registry.addInterceptor(myInterceptor2).addPathPatterns("/**")
        registry.addInterceptor(myInterceptor3).addPathPatterns("/**")
    }


    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(InterceptorConfig::class.java)
    }
}