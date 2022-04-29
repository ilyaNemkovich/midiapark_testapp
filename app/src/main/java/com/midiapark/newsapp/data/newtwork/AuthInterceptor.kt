package com.midiapark.newsapp.data.newtwork

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = getRequestWithToken(chain)
        return chain.proceed(request)
    }

    private fun getRequestWithToken(chain: Interceptor.Chain): Request {
        val original = chain.request()
        return original.newBuilder()
            .url(original.url.toString() + "&token=d6e27eb29d97513d396416e6265b036e")
            .header("Content-Type", "application/json")
            .method(original.method, original.body)
            .build()
    }
}