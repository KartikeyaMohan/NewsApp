package com.kmx.newsapp.data.repository.dataSource

import com.kmx.newsapp.data.models.NewsResponse
import retrofit2.Response

interface NewsRemoteDataSource {

    suspend fun getTopHeadLines(country: String, page: Int): Response<NewsResponse>
    suspend fun getSearchedTopHeadLines(country: String, query: String, page: Int): Response<NewsResponse>
}