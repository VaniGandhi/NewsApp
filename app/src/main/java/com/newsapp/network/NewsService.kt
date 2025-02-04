package com.newsapp.network

import com.newsapp.data.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String = "tesla",
        @Query("from") from: String = "2025-01-03",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String
    ): NewsResponse
}