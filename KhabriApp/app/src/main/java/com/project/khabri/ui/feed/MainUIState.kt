package com.project.khabri.ui.feed

import com.project.khabri.domain.APIError
import com.project.khabri.domain.data.Article
import com.project.khabri.domain.data.NewsCategories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class MainUIState(
    val isLoading: Boolean = false,
    val errorType: APIError? = null,
    val selectedScreen: Int = 0,
    val articles: List<Article> = emptyList(),
    val currentCategories: NewsCategories = NewsCategories.SCIENCE,
    val savedArticles: Flow<List<Article>> = flowOf(emptyList())
//    val items:
)

