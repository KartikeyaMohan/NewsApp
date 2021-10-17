package com.kmx.newsapp.domain.useCase

import com.kmx.newsapp.data.models.NewsResponse
import com.kmx.newsapp.data.utils.Resource
import com.kmx.newsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(country: String, searchQuery: String, page: Int): Resource<NewsResponse> {
        return newsRepository.getSearchNews(country, searchQuery, page)
    }
}