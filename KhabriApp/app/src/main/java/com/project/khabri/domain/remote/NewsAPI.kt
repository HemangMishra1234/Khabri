package com.project.khabri.domain.remote

import com.project.khabri.domain.data.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("health/")
    suspend fun getHealthNews(): Response<List<Article>>

    @GET("business/")
    suspend fun getBusinessNews(): Response<List<Article>>

    @GET("entertainment/")
    suspend fun getEntertainmentNews(): Response<List<Article>>

    @GET("sports/")
    suspend fun getSportsNews(): Response<List<Article>>

    @GET("technology/")
    suspend fun getTechnologyNews(): Response<List<Article>>

    @GET("science/")
    suspend fun getScienceNews(): Response<List<Article>>

    @GET("recommended/")
    suspend fun getRecommendedNews(
        @Query("user-id") uid: String
    ): Response<List<Article>>

}