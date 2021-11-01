package com.ibeilly.demo1.interceptor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object


@Component
class HandlerInterceptor3 : HandlerInterceptor {

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        LOG.info("执行拦截器的preHandle方法: $request.")
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        @Nullable modelAndView: ModelAndView?
    ) {
        LOG.info("执行拦截器的postHandle方法")
    }


    @Throws(Exception::class)
    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        @Nullable e: java.lang.Exception?
    ) {
        LOG.info("执行拦截器的afterCompletion方法")
    }

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(HandlerInterceptor3::class.java)
    }
}
