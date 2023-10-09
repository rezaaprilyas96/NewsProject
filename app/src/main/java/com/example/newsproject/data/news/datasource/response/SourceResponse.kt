package com.example.newsproject.data.news.datasource.response

import com.google.gson.annotations.SerializedName

data class SourceResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null
)
