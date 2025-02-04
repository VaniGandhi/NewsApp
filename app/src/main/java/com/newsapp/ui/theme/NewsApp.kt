package com.newsapp.ui.theme

import android.app.Application
import com.newsapp.data.NewsDatabase
import com.newsapp.NewsRepositoryInstance
import com.newsapp.network.RetrofitInstance

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val database = NewsDatabase.getDatabase(this)
        NewsRepositoryInstance.initialize(RetrofitInstance.api, database)
    }
}