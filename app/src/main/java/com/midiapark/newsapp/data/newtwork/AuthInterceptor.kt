package com.midiapark.newsapp.data.newtwork

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.URI

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = getRequestWithToken(chain)
        return chain.proceed(request)
    }

    private fun getRequestWithToken(chain: Interceptor.Chain): Request {
        val original = chain.request()

        return original.newBuilder()
            .url(
                appendQueryToUri(
                    original.url.toString(),
                    "token=d6e27eb29d97513d396416e6265b036e"
                )
            )
            .header("Content-Type", "application/json")
            .method(original.method, original.body)
            .build()
    }

    private fun appendQueryToUri(uri: String, appendQuery: String): String {
        val oldUri = URI(uri)
        var newQuery: String = oldUri.query.let { it ?: "" }
        newQuery += if (newQuery.isEmpty()) appendQuery else "&$appendQuery"
        return URI(
            oldUri.scheme, oldUri.authority,
            oldUri.path, newQuery, oldUri.fragment
        ).toString()
    }
}