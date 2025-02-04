package com.newsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.newsapp.network.NewsArticle
import com.newsapp.repository.NewsRepository
import com.newsapp.view.NewsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


@ExperimentalCoroutinesApi
class NewsViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: NewsViewModel
    private lateinit var repository: NewsRepository
    private lateinit var observer: Observer<List<NewsArticle>>

    @Before
    fun setUp() {
        repository = mock(NewsRepository::class.java)
        viewModel = NewsViewModel(repository)
        observer = mock(Observer::class.java) as Observer<List<NewsArticle>>
        viewModel.newsArticles.observeForever(observer)
    }


}