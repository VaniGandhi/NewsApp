package com.newsapp.repository

import android.util.Log
import com.newsapp.data.NewsDatabase
import com.newsapp.network.NewsArticle
import com.newsapp.network.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class NewsRepository (private val api: NewsService, private val db: NewsDatabase) {
    fun getNews(): Flow<List<NewsArticle>> = db.newsDao().getNews().flowOn(Dispatchers.IO)

    suspend fun fetchAndCacheNews(): List<NewsArticle> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getNews(apiKey = "c9ee645a60b543d2bf39249a234d3fa0").articles
                val newsArticles = response.map { apiArticle ->
                    NewsArticle(
                        title = apiArticle.title ?: "Untitled",
                        description = apiArticle.description ?: "No description available",
                        urlToImage = apiArticle.urlToImage ?: "",
                        content = apiArticle.content ?: ""
                    )
                }
                db.newsDao().insertNews(newsArticles)
                newsArticles
            } catch (e: HttpException) {
                Log.e("NewsRepository", "HTTP Error: ${e.code()} - ${e.message()}")
                db.newsDao().getNews().first()
            } catch (e: Exception) {
                Log.e("NewsRepository", "Network Error: ${e.message}")
                db.newsDao().getNews().first()
            }
        }
    }

}
