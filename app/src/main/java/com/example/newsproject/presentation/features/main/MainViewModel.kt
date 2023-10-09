package com.example.newsproject.presentation.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.domain.model.NewsModel
import com.example.newsproject.domain.usecase.NewsUseCase
import com.example.newsproject.utils.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _news = MutableLiveData<List<NewsModel>>()
    val news: LiveData<List<NewsModel>>
        get() = _news

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getNews() {
        viewModelScope.launch {
            _isLoading.value = true

            val result = newsUseCase.getNews()
            _isLoading.value = false

            when (result) {
                is ResultState.Success -> _news.value = result.data
                is ResultState.Failure -> _error.value = result.error
            }
        }
    }
}