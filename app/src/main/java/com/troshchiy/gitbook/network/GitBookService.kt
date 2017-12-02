package com.troshchiy.gitbook.network

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface GitBookService {

    @GET("books/all") fun allBooks(): Single<Response<Any>>
}