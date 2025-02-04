package com.newsapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.newsapp.data.NewsDao
import com.newsapp.data.NewsDatabase
import com.newsapp.network.NewsArticle
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsDaoTest {
    private lateinit var database: NewsDatabase
    private lateinit var dao: NewsDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NewsDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.newsDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun `insert and retrieve news from database`() = runBlocking {
        val article = NewsArticle(1, "Title", "Desc", "url", "content")
        dao.insertNews(listOf(article))

        val newsList = dao.getNews()
        assertEquals(1, newsList.first().size)
        assertEquals("Title", newsList.first()[0].title)
    }
}