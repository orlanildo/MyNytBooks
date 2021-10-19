package com.cursoandroid.nybooks.presentation.books

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.nybooks.R
import com.cursoandroid.nybooks.data.repository.BooksApiDataSource
import com.cursoandroid.nybooks.databinding.ActivityBooksBinding
import com.cursoandroid.nybooks.presentation.base.BaseActivity
import com.cursoandroid.nybooks.presentation.details.BookDetailsActivity

class BooksActivity : BaseActivity() {

    private val binding by lazy {
        ActivityBooksBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupToolbar(binding.incToolbarMain.myToolbar, R.string.books_title)

        // Pode usar essa instância quando o viewModel não tiver nenhuma dependência
        //viewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        viewModel = ViewModelProvider(
            viewModelStore,
            BooksViewModel.ViewModelFactory(
                BooksApiDataSource()
            )
        ).get(BooksViewModel::class.java)

        val rcBooks = binding.rcBooks

        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with(rcBooks) {
                    layoutManager = LinearLayoutManager(
                        this@BooksActivity,
                        RecyclerView.VERTICAL,
                        false
                    )

                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val intent = BookDetailsActivity.getStartIntent(
                            this@BooksActivity,
                            book.title,
                            book.description
                        )

                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it.let { viewFlipper ->
                val viewFlipperBooks = binding.viewFlipperBooks
                viewFlipperBooks.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageId ->
                    val tvError = binding.tvError
                    tvError.text = getString(errorMessageId)
                }
            }
        })

        viewModel.getBooks()
    }
}