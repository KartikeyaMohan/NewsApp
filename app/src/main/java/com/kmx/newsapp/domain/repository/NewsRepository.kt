package com.kmx.newsapp.domain.repository

import com.kmx.newsapp.data.models.Article
import com.kmx.newsapp.data.models.NewsResponse
import com.kmx.newsapp.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadlines(country: String, page: Int): Resource<NewsResponse>
    suspend fun getSearchNews(country: String, searchQuery: String, page: Int): Resource<NewsResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}