package com.example.newsproject.di

import com.example.newsproject.data.news.datasource.NewsRemoteDataSource
import com.example.newsproject.data.news.datasource.NewsRemoteDataSourceImpl
import com.example.newsproject.data.news.mapper.NewsMapper
import com.example.newsproject.data.news.repository.NewsRepositoryImpl
import com.example.newsproject.data.routes.ApiServices
import com.example.newsproject.domain.repository.NewsRepository
import com.example.newsproject.domain.usecase.NewsUseCase
import com.example.newsproject.utils.dispatcher.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NewsModule {

    @Provides
    fun provideNewsRemoteDataSource(apiServices: ApiServices): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(apiServices)
    }

    @Provides
    fun provideNewsMapper(): NewsMapper = NewsMapper()

    @Provides
    fun provideNewsRepository(
        remoteDataSource: NewsRemoteDataSource,
        mapper: NewsMapper
    ): NewsRepository {
        return NewsRepositoryImpl(
            remoteDataSource = remoteDataSource,
            mapper = mapper
        )
    }

    @Provides
    fun provideNewsUseCase(
        repository: NewsRepository,
        dispatchers: DispatchersProvider
    ) = NewsUseCase(repository, dispatchers)
}