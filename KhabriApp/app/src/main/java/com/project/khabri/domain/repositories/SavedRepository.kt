package com.project.khabri.domain.repositories

import com.project.khabri.domain.data.Article
import kotlinx.coroutines.flow.Flow

interface SavedRepository {
    fun getSavedArticles(): Flow<List<Article>>

//    fun getSavedArticlesWithoutFlow(): List<Article>

    suspend fun saveArticle(article:Article)

    suspend fun deleteArticle(article: Article)
}