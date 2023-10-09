package com.example.newsproject.domain.repository

import com.example.newsproject.domain.model.NewsModel
import com.example.newsproject.utils.state.ResultState

interface NewsRepository {
    suspend fun getNews(): ResultState<List<NewsModel>>
}