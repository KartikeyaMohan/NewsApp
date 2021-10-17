package com.kmx.newsapp.data.repository.dataSourceImpl

import com.kmx.newsapp.data.db.ArticleDao
import com.kmx.newsapp.data.models.Article
import com.kmx.newsapp.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(private val articleDao: ArticleDao): NewsLocalDataSource {

    override suspend fun saveArticleData(article: Article) {
        articleDao.insert(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles()
    }

    override suspend fun deleteArticleFromDb(article: Article) {
        articleDao.deleteArticleFromDb(article)
    }
}