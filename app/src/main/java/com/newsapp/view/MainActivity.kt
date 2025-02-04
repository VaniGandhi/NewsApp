package com.newsapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsapp.NewsRepositoryInstance
import com.newsapp.NewsViewModelFactory
import com.newsapp.R
import com.newsapp.databinding.ActivityMainBinding
import com.newsapp.network.NewsArticle


class MainActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NewsViewModel by viewModels { NewsViewModelFactory(NewsRepositoryInstance.getRepository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_NewsApp)
        setContentView(binding.root)

        setupRecyclerView()

        viewModel.newsArticles.observe(this, Observer { newsList ->
            binding.progressBar.visibility = View.GONE
            if (newsList.isNotEmpty()) {
                binding.newsRecyclerView.visibility = View.VISIBLE
                binding.errorText.visibility = View.GONE
                (binding.newsRecyclerView.adapter as NewsAdapter).updateNews(newsList)
            } else {
                binding.newsRecyclerView.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
            }
        })


    }

    private fun setupRecyclerView() {
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.newsRecyclerView.adapter = NewsAdapter(emptyList()) { news ->
            openDetailsScreen(news)
        }
    }

    private fun openDetailsScreen(news: NewsArticle) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("title", news.title)
            putExtra("description", news.description)
            putExtra("imageUrl", news.urlToImage)
            putExtra("content", news.content)
        }
        startActivity(intent)
    }
}