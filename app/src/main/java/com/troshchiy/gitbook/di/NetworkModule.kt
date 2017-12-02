package com.troshchiy.gitbook.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.troshchiy.gitbook.BuildConfig
import com.troshchiy.gitbook.network.GitBookService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    private val TIMEOUT = 60L

    @Provides fun service(retrofit: Retrofit) = retrofit.create<GitBookService>(GitBookService::class.java)

    @Provides fun retrofit(okHttpClient: OkHttpClient, gson: Gson) =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .baseUrl(BuildConfig.BASE_URL)
                    .build()

    @Provides fun gson() = GsonBuilder().create()

    @Provides internal fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache) =
            OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .cache(cache)
                    .build()

    @Provides fun loggingInterceptor() = HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }

    @Provides fun cache(cacheFile: File) = Cache(cacheFile, (10 * 1024 * 1024 /* 10MB Cache */).toLong())

    @Provides fun cacheFile(context: Context): File = File(context.cacheDir, "okhttp_cache")

}