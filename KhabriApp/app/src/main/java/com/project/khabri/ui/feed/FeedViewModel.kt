package com.project.khabri.ui.feed

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.khabri.domain.APIResponse
import com.project.khabri.domain.ArticlesError
import com.project.khabri.domain.data.Article
import com.project.khabri.domain.data.NewsCategories
import com.project.khabri.domain.repositories.NewsRepository
import com.project.khabri.domain.repositories.SavedRepository
import com.project.khabri.ui.home.NavigationItem
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class FeedViewModel(
    private val newsRepository: NewsRepository,
    private val localRepository: SavedRepository
) : ViewModel() {
    val savedArticles = localRepository.getSavedArticles()


    private val _uiState = mutableStateOf(MainUIState())
    val uiState: State<MainUIState> = _uiState

    init {
        fetchTopHeadlinesNews()
    }

    private fun fetchTopHeadlinesNews(query: String? = null) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = when(_uiState.value.currentCategories){
                NewsCategories.BUSINESS -> {
                    newsRepository.getBusinessNews()
                }
                NewsCategories.ENTERTAINMENT -> newsRepository.getEntertainmentNews()
                NewsCategories.HEALTH -> newsRepository.getHealthNews()
                NewsCategories.SCIENCE -> newsRepository.getScienceNews()
                NewsCategories.SPORTS -> newsRepository.getSportsNews()
                NewsCategories.TECHNOLOGY -> newsRepository.getTechnologyNews()
            }
//            val result =
//                newsRepository.getNews(categories = _uiState.value.currentCategories, query = query)
            _uiState.value = _uiState.value.copy(isLoading = false)
            when (result) {
                is APIResponse.Success -> {
                    if (result.data.isEmpty()) {
                        _uiState.value = _uiState.value.copy(errorType = ArticlesError.NOT_FOUND)
                        return@launch
                    }
                    _uiState.value = _uiState.value.copy(articles = result.data)
                }

                is APIResponse.Error -> {
                    _uiState.value = _uiState.value.copy(errorType = result.error)
                    Log.e("MainViewModel", "Error fetching news: ${result.error}")
                }
            }
        }
    }

    fun search(query: String) {
        fetchTopHeadlinesNews(query)
    }

    fun changeCategory(categories: NewsCategories) {
        _uiState.value = _uiState.value.copy(currentCategories = categories)
        fetchTopHeadlinesNews()
    }

    fun retryFetchTopHeadlinesNews() {
        _uiState.value = _uiState.value.copy(errorType = null)
        fetchTopHeadlinesNews()
    }

    fun printing() {
        print("Hello hello hello")
    }

    fun changeScreen(selectedScreen: Int) {
        if (selectedScreen in NavigationItem.entries.indices)
            _uiState.value = _uiState.value.copy(selectedScreen = selectedScreen)
    }

    suspend fun isArticleSaved(article: Article): Boolean {
        val articles = localRepository.getSavedArticles()
        return articles.firstOrNull()?.contains(article)?:false
    }

    fun saveArticle(article: Article) {
        _uiState.value = _uiState.value.copy(articles = _uiState.value.articles.map {
            if (it == article) {
                it.copy(isSaved = true)
            } else {
                it
            }
        })
        viewModelScope.launch {
            localRepository.saveArticle(article.copy(isSaved = true))
        }
    }

    fun unSaveArticle(article: Article) {
        _uiState.value = _uiState.value.copy(articles = _uiState.value.articles.map {
            if (it == article) {
                it.copy(isSaved = false)
            } else {
                it
            }
        })
        viewModelScope.launch {
            localRepository.deleteArticle(article)
        }
    }

    fun likeArticle(article: Article) {

    }
}