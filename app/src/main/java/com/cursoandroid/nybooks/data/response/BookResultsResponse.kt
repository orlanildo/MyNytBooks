package com.cursoandroid.nybooks.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookResultsResponse(
//    @Json(name = "rank")
//    val rank: Int,

    @Json(name = "book_details")
    val bookDetailsResponse: List<BookDetailsResponse>
)
