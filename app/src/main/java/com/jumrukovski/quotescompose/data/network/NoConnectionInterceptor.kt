package com.jumrukovski.quotescompose.data.network

import java.net.UnknownHostException
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class NoConnectionInterceptor : Interceptor {

    companion object {
        const val errorCode: Int = 503
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return try {
            chain.proceed(request)
        } catch (e: UnknownHostException) {
            val message = e.message ?: ""
            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(errorCode)
                .message(message)
                .body(message.toResponseBody(null))
                .build()
        }
    }
}
