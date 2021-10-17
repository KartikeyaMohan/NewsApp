package com.kmx.newsapp.data.repository.dataSource

import com.kmx.newsapp.data.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    suspend fun saveArticleData(article: Article)
    fun getSavedArticles(): Flow<List<Article>>
    suspend fun deleteArticleFromDb(article: Article)
}