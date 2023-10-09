package com.example.newsproject.di

import com.example.newsproject.utils.dispatcher.AppDispatchers
import com.example.newsproject.utils.dispatcher.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Provides
    fun provideAppDispatchers(): DispatchersProvider = AppDispatchers()
}