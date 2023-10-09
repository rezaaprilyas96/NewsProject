package com.example.newsproject.domain.usecase

import com.example.newsproject.domain.model.NewsModel
import com.example.newsproject.domain.repository.NewsRepository
import com.example.newsproject.utils.dispatcher.DispatchersProvider
import com.example.newsproject.utils.state.ResultState
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val repository: NewsRepository,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getNews(): ResultState<List<NewsModel>> {
        return withContext(dispatchers.io) {
            repository.getNews()
        }
    }
}