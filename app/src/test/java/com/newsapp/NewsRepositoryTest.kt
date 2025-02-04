package com.newsapp

import com.newsapp.data.NewsDao
import com.newsapp.data.NewsDatabase
import com.newsapp.network.NewsArticle
import com.newsapp.network.NewsService
import com.newsapp.repository.NewsRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.HttpException

class NewsRepositoryTest {

    private lateinit var repository: NewsRepository
    private lateinit var apiService: NewsService
    private lateinit var database: NewsDatabase
    private lateinit var newsDao: NewsDao

    @Before
    fun setUp() {
        apiService = mock(NewsService::class.java)
        database = mock(NewsDatabase::class.java)
        newsDao = mock(NewsDao::class.java)
        `when`(database.newsDao()).thenReturn(newsDao)

        repository = NewsRepository(apiService, database)
    }


    @Test
    fun `fetch news failure returns cached data`() = runBlocking {
        val cachedArticles = listOf(NewsArticle(1, "Cached Title", "Desc", "url", "content"))
        `when`(apiService.getNews(apiKey = anyString())).thenThrow(HttpException::class.java)
        `when`(newsDao.getNews()).thenReturn(flowOf(cachedArticles))

        val result = repository.fetchAndCacheNews()

        assertEquals(cachedArticles, result)
    }
}