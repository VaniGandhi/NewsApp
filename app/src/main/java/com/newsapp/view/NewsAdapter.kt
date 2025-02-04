package com.newsapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newsapp.R
import com.newsapp.network.NewsArticle

class NewsAdapter (private var articles: List<NewsArticle>, private val onClick: (NewsArticle) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.newsTitle)
        private val description: TextView = view.findViewById(R.id.newsDescription)
        private val image: ImageView = view.findViewById(R.id.newsImage)

        fun bind(article: NewsArticle) {
            title.text = article.title
            description.text = article.description
            Glide.with(itemView.context).load(article.urlToImage).placeholder(R.drawable.baseline_image_24).into(image)
            itemView.setOnClickListener { onClick(article) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    fun updateNews(newArticles: List<NewsArticle>) {
        articles = newArticles
        notifyDataSetChanged()
    }
}
