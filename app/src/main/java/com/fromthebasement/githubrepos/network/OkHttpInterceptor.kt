package com.fromthebasement.githubrepos.network

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import timber.log.Timber

/**
 * Intercepts every request and its response for logging purposes
 */
class OkHttpInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Timber.d("OkHttp request url %s", request.url)

        val response = chain.proceed(request)

        val contentType: MediaType? = response.body?.contentType()
        val content = response.body?.string()
        Timber.d("OkHttp response %s", content)

        val wrappedBody: ResponseBody? = content?.toResponseBody(contentType)
        return response.newBuilder().removeHeader("Content-Encoding").body(wrappedBody).build()
    }
}