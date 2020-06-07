package com.example.kotlin30days.di

import com.example.kotlin30days.BuildConfig
import com.example.kotlin30days.data.network.api.ApiInterface
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) builder.addInterceptor(httpLoggingInterceptor)
        builder.addInterceptor(object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                var request=chain.request()
                val url=request.url().newBuilder().addQueryParameter("appId","swsgj").addQueryParameter("ln","ui").build()
                request=request.newBuilder().url(url).method(request.method(), request.body()).build()
                println("----- ${request.body()} : encoded path ${request.url().encodedPath()} : encoded query : ${request.url().encodedQuery()} : request method : ${request.method()}")
                println(" ---- " +request.newBuilder().url(request.url()).method(request.method(), request.body()).build());
                return chain.proceed(request)
            }

        })
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
        retrofit.client(okHttpClient)
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
        return retrofit.build()
    }

    @NotNull
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }



}