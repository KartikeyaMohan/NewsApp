package com.kmx.newsapp.presentation.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.kmx.newsapp.data.models.Article
import com.kmx.newsapp.data.models.NewsResponse
import com.kmx.newsapp.data.utils.Resource
import com.kmx.newsapp.domain.useCase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
): AndroidViewModel(app) {

    val newsHeadlines: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    fun getNewsHeadLines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (isNetworkAvailable(app)) {
                newsHeadlines.postValue(Resource.Loading())
                val apiResult = getNewsHeadlinesUseCase.execute(country, page)
                newsHeadlines.postValue(apiResult)
            } else {
                newsHeadlines.postValue(Resource.Error("Internet connection not available"))
            }
        }
        catch (exception: Exception) {
            newsHeadlines.postValue(Resource.Error(exception.message.toString()))
        }
    }

    fun getSearchNews(country: String, searchQuery: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (isNetworkAvailable(app)) {
                searchNews.postValue(Resource.Loading())
                val apiResult = getSearchedNewsUseCase.execute(country, searchQuery, page)
                searchNews.postValue(apiResult)
            } else {
                searchNews.postValue(Resource.Error("Internet connection not available"))
            }
        }
        catch (exception: Exception) {
            searchNews.postValue(Resource.Error(exception.message.toString()))
        }
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        saveNewsUseCase.execute(article)
    }

    fun getSavedNews() = liveData {
        getSavedNewsUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteSavedNews(article: Article) = viewModelScope.launch {
        deleteSavedNewsUseCase.execute(article)
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        return true;
    }
}