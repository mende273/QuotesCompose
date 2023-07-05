package com.jumrukovski.quotescompose.data.network

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.UnknownHostException

class NoConnectionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return try {
            chain.proceed(request)
        } catch (e: UnknownHostException) {
            val message = e.message ?: ""
            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(503)
                .message(message)
                .body(message.toResponseBody(null))
                .build()
        }
    }
}
