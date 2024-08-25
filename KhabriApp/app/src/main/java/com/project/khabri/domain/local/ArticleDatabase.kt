package com.project.khabri.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.khabri.domain.data.Article

@Database(
    entities = [Article::class],
    version = 1
)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun getArticleDAO(): ArticleDAO
}