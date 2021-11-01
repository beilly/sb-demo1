package com.ibeilly.demo1

import com.fasterxml.jackson.databind.json.JsonMapper
import com.ibeilly.demo1.model.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.SecretKeySpec


/**
 *
 * 对响应体进行加密
 * @author Administrator
 */
@ControllerAdvice
class EncodeResponseBodyAdvice :
    ResponseBodyAdvice<Any?> {

    override fun supports(methodParameter: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        /**
         * 返回对象必须是  Message 并且使用了
         * org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
         */
        return methodParameter.method?.returnType == Message::class.java
    }

    override
    fun beforeBodyWrite(
        @Nullable body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        val data: Any? = (body as? Message<Any?>)?.data
        if (data != null) {
            /**
             * 重写data，进行加密
             */
            body.data = encode(data)
        }
        return body
    }

    private fun encode(data: Any): String {
        val jsonValue: String = JsonMapper().writeValueAsString(data)
        return try {
            val cipher = Base64.getEncoder()
                .encodeToString(AESUtils.encrypt(jsonValue.toByteArray(StandardCharsets.UTF_8), KEY))
            if (LOGGER.isDebugEnabled) {
                LOGGER.debug("响应体AES加密：raw={},cipher={}", jsonValue, cipher)
            }
            cipher
        } catch (e: Exception) {
            "对数据加密异常:" + e.message
        }
    }

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(EncodeResponseBodyAdvice::class.java)

        /**
         * 128位AES密钥
         */
        val KEY = "9f5d54580044d478".toByteArray()
    }
}


/**
 *
 * AES
 *
 *
 */
object AESUtils {
    private const val AES_ALGORITHM = "AES/ECB/PKCS5Padding"

    // 获取 cipher
    @Throws(java.lang.Exception::class)
    private fun getCipher(key: ByteArray, model: Int): Cipher {
        val secretKeySpec = SecretKeySpec(key, "AES")
        val cipher = Cipher.getInstance(AES_ALGORITHM)
        cipher.init(model, secretKeySpec)
        return cipher
    }

    // AES加密
    @Throws(
        java.lang.Exception::class,
        NoSuchPaddingException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    fun encrypt(data: ByteArray?, key: ByteArray): ByteArray {
        val cipher = getCipher(key, Cipher.ENCRYPT_MODE)
        return cipher.doFinal(data)
    }

    // AES解密
    @Throws(
        java.lang.Exception::class,
        NoSuchPaddingException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    fun decrypt(data: ByteArray?, key: ByteArray): ByteArray {
        val cipher = getCipher(key, Cipher.DECRYPT_MODE)
        return cipher.doFinal(data)
    }
}