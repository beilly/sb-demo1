package com.ibeilly.demo1

import com.ibeilly.demo1.interceptor.HandlerInterceptor1
import com.ibeilly.demo1.interceptor.HandlerInterceptor2
import com.ibeilly.demo1.interceptor.HandlerInterceptor3
import lombok.AllArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@AllArgsConstructor
@EnableConfigurationProperties
class InterceptorConfig: WebMvcConfigurer {

    @Autowired
    lateinit var myInterceptor1: HandlerInterceptor1

    @Autowired
    lateinit var myInterceptor2: HandlerInterceptor2

    @Autowired
    lateinit var myInterceptor3: HandlerInterceptor3

    override fun addInterceptors(registry: InterceptorRegistry) {
        LOG.info("addInterceptors")
        registry.addInterceptor(myInterceptor1).order(100)
        registry.addInterceptor(myInterceptor2).addPathPatterns("/*").order(10)
        registry.addInterceptor(myInterceptor3).addPathPatterns("/*").order(80)
    }


    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(InterceptorConfig::class.java)
    }
}