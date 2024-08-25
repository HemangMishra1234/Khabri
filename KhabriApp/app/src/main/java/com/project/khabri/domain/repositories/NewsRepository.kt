package com.project.khabri.domain.repositories

import com.project.khabri.domain.APIResponse
import com.project.khabri.domain.data.Article
import com.project.khabri.domain.data.NewsCategories
import com.project.khabri.domain.ArticlesError


interface NewsRepository {
    suspend fun getSportsNews(): APIResponse<List<Article>, ArticlesError>

    suspend fun getHealthNews(): APIResponse<List<Article>, ArticlesError>

    suspend fun getBusinessNews(): APIResponse<List<Article>, ArticlesError>

    suspend fun getEntertainmentNews(): APIResponse<List<Article>, ArticlesError>

    suspend fun getTechnologyNews(): APIResponse<List<Article>, ArticlesError>

    suspend fun getRecommendedNews(uid: String) : APIResponse<List<Article>, ArticlesError>

    suspend fun getScienceNews(): APIResponse<List<Article>, ArticlesError>
}