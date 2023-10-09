package com.example.newsproject.data.news.datasource.response

import com.google.gson.annotations.SerializedName


data class NewsListResponse(
    @SerializedName("articles")
    val articles: List<ArticleResponse>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = null
)