package com.project.khabri.domain.remote

import com.project.khabri.data.remote.ArticleNew
import com.project.khabri.data.remote.New
import com.project.khabri.domain.data.Article
import com.project.khabri.domain.data.UserData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NewsAPI {
    @GET("health/")
    suspend fun getHealthNews(): Response<ArticleNew>

    @GET("business/")
    suspend fun getBusinessNews():Response<ArticleNew>

    @GET("entertainment/")
    suspend fun getEntertainmentNews(): Response<ArticleNew>

    @GET("sports/")
    suspend fun getSportsNews(): Response<ArticleNew>

    @GET("technology/")
    suspend fun getTechnologyNews(): Response<ArticleNew>

    @GET("science/")
    suspend fun getScienceNews(): Response<ArticleNew>

    @GET("recommended/")
    suspend fun getRecommendedNews(
        @Query("user-id") uid: String
    ):Response<ArticleNew>

    @POST("user/")
    suspend fun updateUserData(@Body userData: UserData): Response<UserData>

}