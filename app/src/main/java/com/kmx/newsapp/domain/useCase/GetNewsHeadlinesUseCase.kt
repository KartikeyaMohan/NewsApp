package com.kmx.newsapp.domain.useCase

import com.kmx.newsapp.data.models.NewsResponse
import com.kmx.newsapp.data.utils.Resource
import com.kmx.newsapp.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(country: String, page: Int): Resource<NewsResponse> {
        return newsRepository.getNewsHeadlines(country, page)
    }
}