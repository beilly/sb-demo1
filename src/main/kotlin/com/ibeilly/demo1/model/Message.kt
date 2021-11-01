package com.ibeilly.demo1.model

import lombok.Data

@Data
class Message<T>(var data: T?) {
    var msg: String = ""
    var code: Int = 0

    companion object{
        fun <T> success(data: T)
            = Message(data)

        fun <T> fail(data: T?)
                = Message(data).apply {
                    code = -1
        }

    }
}
