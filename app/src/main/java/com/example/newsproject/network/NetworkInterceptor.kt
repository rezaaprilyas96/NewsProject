package com.example.newsproject.network

import com.example.newsproject.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter("apikey", BuildConfig.API_KEY)
            .build()
        request = request.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}