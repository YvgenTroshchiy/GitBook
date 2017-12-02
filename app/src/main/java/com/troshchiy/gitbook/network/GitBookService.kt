package com.troshchiy.gitbook.network

import retrofit2.Response
import retrofit2.http.GET
import rx.Single

interface GitBookService {

    @GET("books/all") fun allBooks(): Single<Response<Any>>
}