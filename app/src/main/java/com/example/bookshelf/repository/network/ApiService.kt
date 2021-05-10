package com.example.bookshelf.repository.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://www.googleapis.com/"

/**
 * Global HTTP client to query and fetch.
 */
object ApiService {
    // Use this when adding authentication headers (eg. signing in user),
    private val interceptor = Interceptor {
        val request = it.request().newBuilder().addHeader("", "").build()
        it.proceed(request)
    }

    // Logger for debugging purposes.
    private val httpLoggerInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    // Uncomment this line when ready to authenticate.
    // val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
    private val okHttpClient = OkHttpClient.Builder().addNetworkInterceptor(httpLoggerInterceptor).build()
    val retrofitClient: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
}

inline fun <reified T: Any> createApiService(api: Class<T>): T = ApiService.retrofitClient.create(api)