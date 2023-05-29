package com.example.dannvgithub.di

import com.example.dannvgithub.api.ApiConstants
import com.example.dannvgithub.api.GithubApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    @Singleton
    @Provides
    fun providerLogger(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
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

    @Provides
    fun providerRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    }

    @Provides
    fun providerGitHubService(retrofit: Retrofit): GithubApiServices {
        return retrofit.create(GithubApiServices::class.java)

    }
}