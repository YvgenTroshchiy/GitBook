package com.troshchiy.gitbook.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.troshchiy.gitbook.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val timeout = 60L

    @Provides fun gson(): Gson = GsonBuilder().create()

    @Provides @Singleton
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache) =
            OkHttpClient.Builder()
                    .connectTimeout(timeout, TimeUnit.SECONDS)
                    .readTimeout(timeout, TimeUnit.SECONDS)
                    .writeTimeout(timeout, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .cache(cache)
                    .build()

    @Provides @Singleton
    fun loggingInterceptor() =
            HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }

    @Provides @Singleton
    fun cache(cacheFile: File) = Cache(cacheFile, (10 * 1024 * 1024 /* 10MB Cache */).toLong())

    @Provides @Singleton
    fun cacheFile(context: Context): File = File(context.cacheDir, "okhttp_cache")

}