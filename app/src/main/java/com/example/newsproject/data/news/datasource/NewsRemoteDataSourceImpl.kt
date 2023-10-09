package com.example.newsproject.data.news.datasource

import com.example.newsproject.data.news.datasource.response.ArticleResponse
import com.example.newsproject.data.news.datasource.response.NewsListResponse
import com.example.newsproject.data.routes.ApiServices
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val apiServices: ApiServices
) : NewsRemoteDataSource {
    override suspend fun getListNews(): Response<NewsListResponse> {
        return apiServices.getListNews()
    }
}