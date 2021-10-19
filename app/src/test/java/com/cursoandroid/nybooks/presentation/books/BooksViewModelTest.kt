package com.cursoandroid.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cursoandroid.nybooks.R
import com.cursoandroid.nybooks.data.BooksResult
import com.cursoandroid.nybooks.data.model.Book
import com.nhaarman.mockitokotlin2.verify
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.cursoandroid.nybooks.data.repository.IBooksRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private lateinit var viewModel: BooksViewModel

//    Isso é pra quando estiver utilizando um runner diferente do Mockito
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//    }

    @Test
    fun `when view model getBooks get success then sets booksLiveData`() {
        // Arrange
        val books = listOf(
            Book("Title 01", "author 01", "desc 01"),
            Book("Title 02", "author 02", "desc 02"),
        )

        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel = BooksViewModel(resultSuccess)

        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when view model getBooks get API error then sets viewFlipperData`() {
        // Arrange
        val resultApiError401 = MockRepository(BooksResult.ApiError(401))
        viewModel = BooksViewModel(resultApiError401)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_401))

        // Arrange
        val resultApiError400 = MockRepository(BooksResult.ApiError(400))
        viewModel = BooksViewModel(resultApiError400)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_400))
    }

    @Test
    fun `when view model getBooks get server error then sets viewFlipperData`() {
        // Arrange
        val resultServerError = MockRepository(BooksResult.ServerError)
        viewModel = BooksViewModel(resultServerError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_500))
    }
}

class MockRepository(private val result: BooksResult) : IBooksRepository {
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        booksResultCallback(result)
    }
}