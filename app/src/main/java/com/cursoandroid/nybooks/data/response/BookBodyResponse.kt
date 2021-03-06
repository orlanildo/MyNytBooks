package com.cursoandroid.nybooks.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookBodyResponse(
    @Json(name = "results")
    val bookResultsResponse: List<BookResultsResponse>
)
