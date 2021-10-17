package com.kmx.newsapp.data.repository.dataSourceImpl

import com.kmx.newsapp.data.api.NewsApiService
import com.kmx.newsapp.data.models.NewsResponse
import com.kmx.newsapp.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService
): NewsRemoteDataSource {

    override suspend fun getTopHeadLines(country: String, page: Int): Response<NewsResponse> {
        return newsApiService.getTopHeadlines(country, page)
    }

    override suspend fun getSearchedTopHeadLines(
        country: String,
        query: String,
        page: Int
    ): Response<NewsResponse> {
        return newsApiService.getSearchedTopHeadlines(country, query, page)
    }
}