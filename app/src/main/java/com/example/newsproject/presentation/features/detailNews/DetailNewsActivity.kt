package com.example.newsproject.presentation.features.detailNews

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.newsproject.R
import com.example.newsproject.base.BindingActivity
import com.example.newsproject.databinding.ActivityDetailNewsBinding
import com.example.newsproject.domain.model.NewsModel
import com.example.newsproject.presentation.features.webview.WebViewActivity
import com.example.newsproject.utils.extensions.serializable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailNewsActivity : BindingActivity<ActivityDetailNewsBinding>() {

    private var newsModel: NewsModel? = null

    override fun initViewBinding(inflater: LayoutInflater): ActivityDetailNewsBinding {
        return ActivityDetailNewsBinding.inflate(inflater)
    }

    override fun renderView() {
        newsModel = intent.serializable(EXTRA_NEWS_MODEL)
        setupActionBar()
        setupValue()
        setupOnClick()
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    private fun setupValue() {
        with(binding) {
            imgDetailNews.load(newsModel?.urlToImage.orEmpty()) {
                crossfade(true)
                crossfade(500)
                transformations(RoundedCornersTransformation(10f))
                placeholder(R.drawable.file)
                error(R.drawable.ic_fail)
            }

            tvValueSourcesDetailNews.text = newsModel?.source
            tvValueTitleDetailNews.text = newsModel?.title
            tvValueDescriptionDetailNews.text = newsModel?.description
            tvValueUrlDetailNews.text = newsModel?.url
            tvPublishedDetailNews.text = newsModel?.published
        }
    }

    private fun setupOnClick() {
        with(binding) {
            tvValueUrlDetailNews.setOnClickListener {
                startActivity(
                    WebViewActivity.createIntent(
                        context = this@DetailNewsActivity,
                        url = newsModel?.url
                    )
                )
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val EXTRA_NEWS_MODEL = "extra_news_model"

        fun createIntent(
            context: Context,
            newsModel: NewsModel? = null,
        ): Intent {
            return Intent(context, DetailNewsActivity::class.java).apply {
                putExtra(EXTRA_NEWS_MODEL, newsModel)
            }
        }
    }
}