package com.cursoandroid.nybooks.data.repository

import com.cursoandroid.nybooks.data.BooksResult

interface IBooksRepository {

    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}