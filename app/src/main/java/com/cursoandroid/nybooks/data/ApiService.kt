package com.cursoandroid.nybooks.data

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiService {
    private fun initRetrofit(): Retrofit {


        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/books/v3/")
            .addConverterFactory(
                MoshiConverterFactory.create()
            )
            .build()
    }

    val service: NYTServices = initRetrofit().create(NYTServices::class.java)
}