package com.troshchiy.gitbook.network

import com.troshchiy.gitbook.models.Books
import io.reactivex.Single
import retrofit2.http.GET

interface GitBookService {

    @GET("books/all") fun allBooks(): Single<Books>
}