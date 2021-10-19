package com.cursoandroid.nybooks.data

import com.cursoandroid.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "v5uSzCVamBbe2q3JbrAGDW39CaE6ABXU",
        @Query("list") List: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}