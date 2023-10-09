package com.example.newsproject.presentation.features.main

import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsproject.R
import com.example.newsproject.base.BindingActivity
import com.example.newsproject.databinding.ActivityMainBinding
import com.example.newsproject.domain.model.NewsModel
import com.example.newsproject.presentation.adapter.NewsAdapter
import com.example.newsproject.presentation.features.detailNews.DetailNewsActivity
import com.example.newsproject.utils.helpers.ColorPalette
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>() {
    private val newsDetailAdapter by lazy { NewsAdapter() }
    private val viewModel: MainViewModel by viewModels()
    private var list: List<NewsModel>? = null

    override fun initViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun renderView() {
        setupActionBar()
        setupSwipeRefresh()
        setupNewsList()
        setupObserve()
        requestGetNews()
        setUpDoAfterChange()
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    private fun setupSwipeRefresh() {
        with(binding) {
            srMain.setColorSchemeColors(
                ColorPalette.DARK_BLUE,
                ColorPalette.YOUNG_BLUE,
                ColorPalette.BLUE_WEDGE_WOOD
            )
            srMain.setOnRefreshListener {
                requestGetNews()
            }
        }
    }

    private fun setUpDoAfterChange() {
        binding.etMainSearchData.doAfterTextChanged {
            search(it.toString())
        }
    }

    private fun requestGetNews(){
        viewModel.getNews()
    }

    private fun setupObserve(){
        viewModel.news.observe(this){news ->
            renderNewsList(news)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.srMain.isRefreshing = isLoading
        }

        viewModel.error.observe(this) { error ->
            toast(error)
        }
    }

    private fun setupNewsList(){
        with(binding){
            newsDetailAdapter.setOnItemClickListener { news ->
                startActivity(
                    DetailNewsActivity.createIntent(
                        context = this@MainActivity,
                        newsModel = news
                    )
                )
            }

            rvMain.apply {
                setHasFixedSize(true)
                setItemViewCacheSize(20)
                layoutManager = GridLayoutManager(this@MainActivity, 2)
                adapter = newsDetailAdapter
                isNestedScrollingEnabled = true
            }
        }
    }

    private fun renderNewsList(newsModels: List<NewsModel>) {
        list = newsModels
        newsDetailAdapter.setData(newsModels)
        binding.etMainSearchData.setText("")
    }

    private fun search(search: String) {
        val searchnya = search.lowercase()
        val searchNews = ArrayList<NewsModel>()

        list.orEmpty().filter {
            it.author.lowercase().contains(searchnya) ||
            it.title.lowercase().contains(searchnya)
        }.forEach {
            searchNews.add(it)
        }

        newsDetailAdapter.setData(searchNews)
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
}