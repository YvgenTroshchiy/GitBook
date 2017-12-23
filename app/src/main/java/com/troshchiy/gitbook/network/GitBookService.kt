package com.troshchiy.gitbook.network

import com.troshchiy.gitbook.network.models.Books
import io.reactivex.Observable
import retrofit2.http.GET

interface GitBookService {

    @GET("books/all") fun allBooks(): Observable<Books>
}