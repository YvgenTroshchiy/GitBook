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
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    private val timeout = 60L

    @Provides fun service(retrofit: Retrofit): GitBookService = retrofit.create<GitBookService>(GitBookService::class.java)

    @Provides fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides fun gson(): Gson = GsonBuilder().create()

    @Provides internal fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache) =
            OkHttpClient.Builder()
                    .connectTimeout(timeout, TimeUnit.SECONDS)
                    .readTimeout(timeout, TimeUnit.SECONDS)
                    .writeTimeout(timeout, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .cache(cache)
                    .build()

    @Provides fun loggingInterceptor() = HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }

    @Provides fun cache(cacheFile: File) = Cache(cacheFile, (10 * 1024 * 1024 /* 10MB Cache */).toLong())

    @Provides fun cacheFile(context: Context): File = File(context.cacheDir, "okhttp_cache")

}