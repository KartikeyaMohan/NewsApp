package com.kmx.newsapp.data.repository

import com.kmx.newsapp.data.models.Article
import com.kmx.newsapp.data.models.NewsResponse
import com.kmx.newsapp.data.repository.dataSource.NewsLocalDataSource
import com.kmx.newsapp.data.repository.dataSource.NewsRemoteDataSource
import com.kmx.newsapp.data.utils.Resource
import com.kmx.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
): NewsRepository {
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<NewsResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadLines(country, page))
    }

    override suspend fun getSearchNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<NewsResponse> {
        return responseToResource(newsRemoteDataSource.getSearchedTopHeadLines(country, searchQuery, page))
    }

    override suspend fun saveNews(article: Article) {
        newsLocalDataSource.saveArticleData(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsLocalDataSource.deleteArticleFromDb(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return newsLocalDataSource.getSavedArticles()
    }

    private fun responseToResource(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let {
                result -> return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}