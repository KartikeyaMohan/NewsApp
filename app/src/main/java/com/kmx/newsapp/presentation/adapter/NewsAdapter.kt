package com.kmx.newsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kmx.newsapp.data.models.Article
import com.kmx.newsapp.databinding.NewsListItemBinding

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(
        val binding: NewsListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.title.text = article.title
            binding.description.text = article.description
            binding.publishedDate.text = article.publishedAt
            binding.source.text = article.source?.name
            Glide.with(binding.newsImage.context).load(article.urlToImage).into(binding.newsImage)
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener: ((Article)->Unit)? = null

    fun setOnItemClickListener(listener : (Article) -> Unit) {
        onItemClickListener = listener
    }
}