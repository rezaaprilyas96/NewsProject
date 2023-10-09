package com.example.newsproject.data.news.mapper

import com.example.newsproject.data.news.datasource.response.ArticleResponse
import com.example.newsproject.domain.model.NewsModel

class NewsMapper {

    fun mapResponseToDomain(response: ArticleResponse): NewsModel {
        return with(response) {
            NewsModel(
                urlToImage = urlToImage.orEmpty(),
                source = source?.name.orEmpty(),
                description = description.orEmpty(),
                author = author.orEmpty(),
                url = url.orEmpty(),
                title = title.orEmpty(),
                published = publishedAt.orEmpty()
            )
        }
    }

    fun mapResponseToListDomain(listResponse: List<ArticleResponse>): List<NewsModel> {
        return listResponse.map(::mapResponseToDomain)
    }
}