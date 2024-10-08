package com.project.khabri.domain.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.project.khabri.domain.data.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {

    @Upsert
    suspend fun upsertArticle(article: Article)

    @Delete
    suspend fun deleteContact(article: Article)

    @Query("SELECT * FROM article")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * FROM article")
    fun getArticlesWithoutFlow(): List<Article>
}