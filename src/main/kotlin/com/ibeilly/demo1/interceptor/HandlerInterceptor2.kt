package com.ibeilly.demo1.interceptor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object


@Component
class HandlerInterceptor2 : HandlerInterceptor {
    @Override
    @Throws(Exception::class)
    fun preHandle(
        httpServletRequest: HttpServletRequest?,
        httpServletResponse: HttpServletResponse?,
        o: Object?
    ): Boolean {
        LOG.info("执行拦截器的preHandle方法")
        return true
    }

    @Override
    @Throws(Exception::class)
    fun postHandle(
        httpServletRequest: HttpServletRequest?,
        httpServletResponse: HttpServletResponse?,
        o: Object?,
        modelAndView: ModelAndView?
    ) {
        LOG.info("执行拦截器的postHandle方法")
    }

    @Override
    @Throws(Exception::class)
    fun afterCompletion(
        httpServletRequest: HttpServletRequest?,
        httpServletResponse: HttpServletResponse?,
        o: Object?,
        e: Exception?
    ) {
        LOG.info("执行拦截器的afterCompletion方法")
    }

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(HandlerInterceptor2::class.java)
    }
}
