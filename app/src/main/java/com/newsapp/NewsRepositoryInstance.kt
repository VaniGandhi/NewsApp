package com.newsapp

import com.newsapp.data.NewsDatabase
import com.newsapp.network.NewsService
import com.newsapp.repository.NewsRepository

object NewsRepositoryInstance {
    private lateinit var repository: NewsRepository


fun initialize(api: NewsService, database: NewsDatabase) {
    repository = NewsRepository(api, database)
}

fun getRepository(): NewsRepository {
    if (!::repository.isInitialized) {
        throw IllegalStateException("NewsRepositoryInstance not initialized")
    }
    return repository

}
}
