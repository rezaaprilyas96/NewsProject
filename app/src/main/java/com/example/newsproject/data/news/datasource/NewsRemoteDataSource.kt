package com.example.newsproject.data.news.datasource

import com.example.newsproject.data.news.datasource.response.NewsListResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getListNews(): Response<NewsListResponse>
}