package com.troshchiy.gitbook.di

import com.google.gson.Gson
import com.troshchiy.gitbook.BuildConfig
import com.troshchiy.gitbook.network.GitBookService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class GitBookServiceModule {

    @Provides @Singleton
    fun service(retrofit: Retrofit): GitBookService =
            retrofit.create<GitBookService>(GitBookService::class.java)

    @Provides @Singleton
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
}