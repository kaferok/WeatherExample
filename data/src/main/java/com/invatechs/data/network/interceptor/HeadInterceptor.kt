package com.invatechs.data.network.interceptor

import com.invatechs.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeadInterceptor : Interceptor {

    companion object {
        private const val RAPID_HOST = "x-rapidapi-host"
        private const val RAPID_HOST_VALUE = "community-open-weather-map.p.rapidapi.com"
        private const val RAPID_KEY = "x-rapidapi-key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
            .newBuilder()
            .addHeader(RAPID_HOST, RAPID_HOST_VALUE)
            .addHeader(RAPID_KEY, BuildConfig.API_KEY)
            .build()
        return chain.proceed(newRequest)
    }
}