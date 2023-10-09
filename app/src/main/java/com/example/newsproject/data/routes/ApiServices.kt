package com.example.newsproject.data.routes

import com.example.newsproject.data.news.datasource.response.NewsListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("top-headlines?country=us&category")
    suspend fun getListNews(): Response<NewsListResponse>
}