package com.newsapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.newsapp.network.NewsArticle

@Database(entities = [NewsArticle::class], version = 1,exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile private var instance: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                ).build()
                instance = newInstance
                newInstance
            }
        }
    }
}
