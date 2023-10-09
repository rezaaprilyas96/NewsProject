package com.example.newsproject.data.news.repository

import com.example.newsproject.data.news.datasource.NewsRemoteDataSource
import com.example.newsproject.data.news.mapper.NewsMapper
import com.example.newsproject.domain.model.NewsModel
import com.example.newsproject.domain.repository.NewsRepository
import com.example.newsproject.utils.state.ResultState
import java.net.UnknownHostException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val mapper: NewsMapper,
) : NewsRepository {
    override suspend fun getNews(): ResultState<List<NewsModel>> {
        return try {
            val response = remoteDataSource.getListNews()
            return if (response.isSuccessful) {
                val results = response.body()?.articles.orEmpty()
                val newsList = mapper.mapResponseToListDomain(results)
                ResultState.Success(newsList)
            } else {
                ResultState.Failure(response.message())
            }
        } catch (exception: UnknownHostException) {
            ResultState.Failure(exception.message.toString())
        }
    }
}