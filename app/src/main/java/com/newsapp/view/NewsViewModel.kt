package com.newsapp.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapp.network.NewsArticle
import com.newsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel (private val repository: NewsRepository) : ViewModel() {
    private val _newsArticles = MutableLiveData<List<NewsArticle>>()
    val newsArticles: LiveData<List<NewsArticle>> get() = _newsArticles

    init {
        refreshNews()
    }

    fun refreshNews() {
        viewModelScope.launch {
            val articles = repository.fetchAndCacheNews()
            Log.d("NewsViewModel", "refreshNews:${repository.fetchAndCacheNews()} ")
            _newsArticles.postValue(articles)
        }
    }
}