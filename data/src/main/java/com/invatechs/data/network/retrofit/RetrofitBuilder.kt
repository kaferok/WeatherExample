package com.invatechs.data.network.retrofit

import com.invatechs.data.BuildConfig
import com.invatechs.data.network.interceptor.HeadInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    internal fun build(): Retrofit {
        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(getLoggingInterceptor())
            addInterceptor(HeadInterceptor())
        }
        return build(httpClient.build())
    }

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun build(client: OkHttpClient) =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.SERVER_URL)
            .client(client)
            .build()

}