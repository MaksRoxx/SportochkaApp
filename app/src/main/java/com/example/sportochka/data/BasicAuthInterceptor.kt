package com.example.sportochka.data

import okhttp3.Credentials.basic
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class BasicAuthInterceptor(user: String?, password: String?) : Interceptor {
    private val credentials: String

    init {
        credentials = basic(user!!, password!!)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val requestBuilder: Request.Builder = original.newBuilder()
            .header("Authorization", credentials)
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}