package com.example.bookshelf.repository.network

import com.example.bookshelf.repository.network.model.BooksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {

    @GET("books/v1/volumes")
    fun getBooksResponse(@Query("q") searchTerm: String,
                         @Query("apiKey") apiKey: String): Call<BooksResponse>

}