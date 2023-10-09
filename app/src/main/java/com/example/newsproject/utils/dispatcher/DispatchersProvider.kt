package com.example.newsproject.utils.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    val ui: CoroutineDispatcher
    val io: CoroutineDispatcher
}