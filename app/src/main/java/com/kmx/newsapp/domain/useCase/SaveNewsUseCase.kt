package com.kmx.newsapp.domain.useCase

import com.kmx.newsapp.data.models.Article
import com.kmx.newsapp.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) = newsRepository.saveNews(article)
}