package com.example.newsproject.utils.dispatcher

import com.example.newsproject.utils.dispatcher.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestDispatchers : DispatchersProvider {
    override val ui: CoroutineDispatcher = Dispatchers.Unconfined
    override val io: CoroutineDispatcher = Dispatchers.Unconfined
}