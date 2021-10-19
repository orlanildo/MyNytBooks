package com.cursoandroid.nybooks.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cursoandroid.nybooks.R
import com.cursoandroid.nybooks.databinding.ActivityBookDetailsBinding
import com.cursoandroid.nybooks.presentation.base.BaseActivity

class BookDetailsActivity : BaseActivity() {

    private val binding by lazy {
        ActivityBookDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupToolbar(
            binding.incToolbarDetails.myToolbar,
            R.string.books_details,
            true
        )

        val tvTitle = binding.tvTitleDetails
        val tvDesc = binding.tvDescDetails

        tvTitle.text = intent.getStringExtra(EXTRA_TITLE)
        tvDesc.text = intent.getStringExtra(EXTRA_DESC)
    }

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESC = "EXTRA_DESC"

        fun getStartIntent(context: Context, title: String, desc: String): Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESC, desc)
            }
        }
    }
}