package com.example.newsproject.domain.model

import java.io.Serializable

class NewsModel(
    val urlToImage: String,
    val source: String,
    val description: String,
    val author: String,
    val url: String,
    val title: String,
    val published: String
) : Serializable