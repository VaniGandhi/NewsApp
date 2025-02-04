package com.newsapp.network

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_articles")
data class NewsArticle( @PrimaryKey(autoGenerate = true) val id: Int = 0,
                        val title: String,
                        val description: String?,
                        val urlToImage: String?,
                        val content: String?)
