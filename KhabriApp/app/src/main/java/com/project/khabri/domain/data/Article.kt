package com.project.khabri.domain.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity()
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val content: String,
    val description: String,
    val image: String,
    val publishedAt: String,
    val sourceName: String,
    val sourceUrl: String,
    val title: String,
    @PrimaryKey
    val url: String,
    val isLiked: Boolean = false,
    val isSaved: Boolean = false
): Parcelable