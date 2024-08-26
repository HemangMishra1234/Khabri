package com.project.khabri.domain.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "article")
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = false)
    val unique_id: String,
    val content: String,
    val description: String,
    val category: String = "",
    val image: String,
    val published_at: String,
    val source_name: String,
    val source_url: String,
    val country: String,
    val title: String,
    val url: String,
    val is_real: Float,
    val isLiked: Boolean = false,
    val isSaved: Boolean = false
): Parcelable