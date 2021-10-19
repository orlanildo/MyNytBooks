package com.cursoandroid.nybooks.data.response

import com.cursoandroid.nybooks.data.model.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookDetailsResponse(
    @Json(name = "title")
    val title: String,

    @Json(name = "author")
    val author: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "price")
    val price: Int,
) {
    fun getBookModel() = Book(
        title = this.title,
        author = this.author,
        description = this.description
    )
}
