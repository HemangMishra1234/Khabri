package com.project.khabri.domain.repositories

import com.project.khabri.domain.data.Article
import com.project.khabri.domain.repositories.SavedRepository
import com.project.khabri.domain.local.ArticleDAO
import kotlinx.coroutines.flow.Flow

class SavedRepositoryImpl(private val dao: ArticleDAO): SavedRepository {
    override fun getSavedArticles(): Flow<List<Article>> {
        return dao.getArticles()
    }

//    override fun getSavedArticlesWithoutFlow(): List<Article> {
//        return dao.getArticlesWithoutFlow()
//    }

    override suspend fun saveArticle(article: Article) {
        dao.upsertArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        dao.deleteContact(article)
    }
}