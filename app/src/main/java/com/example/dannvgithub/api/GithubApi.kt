package com.example.dannvgithub.api

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.*
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class GithubApi {

    fun initClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = Builder()
        client.addInterceptor(interceptor)

        client.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()

            val request: Request = original.newBuilder()
                .header("Authorization", "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")
                .build()
            chain.proceed(request)
        })
        return client.build()
    }
    var retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(initClient())
        .build()

    var service: GithubApiServices = retrofit.create(GithubApiServices::class.java)

    companion object {
        var instance : GithubApi? = null

        fun getInstanceApi(): GithubApi {
            if (instance == null) {
                instance = GithubApi()
            }
            return instance!!
        }
    }
}