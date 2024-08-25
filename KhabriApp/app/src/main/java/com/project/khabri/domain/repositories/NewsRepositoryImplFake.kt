package com.project.khabri.domain.repositories

import com.project.khabri.domain.APIResponse
import com.project.khabri.domain.ArticlesError
import com.project.khabri.domain.data.Article
import kotlinx.coroutines.delay

class NewsRepositoryImplFake: NewsRepository {

    override suspend fun getSportsNews(): APIResponse<List<Article>, ArticlesError> {
                return try {
            delay(3000)
//            throw Exception()
            APIResponse.Success(listOf(article, article, article, article, article, article))
        }
        catch (e: Exception){
            APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
        }
    }

    override suspend fun getHealthNews(): APIResponse<List<Article>, ArticlesError> {
        return try {
            delay(3000)
//            throw Exception()
            APIResponse.Success(listOf(article, article, article, article, article, article))
        }
        catch (e: Exception){
            APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
        }
    }

    override suspend fun getBusinessNews(): APIResponse<List<Article>, ArticlesError> {
        return try {
            delay(3000)
//            throw Exception()
            APIResponse.Success(listOf(article, article, article, article, article, article))
        }
        catch (e: Exception){
            APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
        }
    }

    override suspend fun getEntertainmentNews(): APIResponse<List<Article>, ArticlesError> {
        return try {
            delay(3000)
//            throw Exception()
            APIResponse.Success(listOf(article, article, article, article, article, article))
        }
        catch (e: Exception){
            APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
        }
    }

    override suspend fun getTechnologyNews(): APIResponse<List<Article>, ArticlesError> {
        return try {
            delay(3000)
//            throw Exception()
            APIResponse.Success(listOf(article, article, article, article, article, article))
        }
        catch (e: Exception){
            APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
        }
    }

    override suspend fun getRecommendedNews(uid: String): APIResponse<List<Article>, ArticlesError> {
        return try {
            delay(3000)
//            throw Exception()
            APIResponse.Success(listOf(article, article, article, article, article, article))
        }
        catch (e: Exception){
            APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
        }
    }

    override suspend fun getScienceNews(): APIResponse<List<Article>, ArticlesError> {
        return try {
            delay(3000)
//            throw Exception()
            APIResponse.Success(listOf(article, article, article, article, article, article))
        }
        catch (e: Exception){
            APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
        }
    }
}

val article = Article(
    id=0,
    content = "This is the full content of the dummy article. It provides in-depth information about the topic discussed in the article.",
    description = "This is a short description of the dummy article, summarizing its main points.",
    image = "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/BB1pOttF.img?w=768&h=432&m=6&x=356&y=130&s=105&d=105",
    publishedAt = "2024-07-12T10:00:00Z",
    title = "Budget 2024: Income Tax Expectations From Finance Minister Nirmala Sitharaman",
    url = "https://medium.com/@kevinnzou/using-webview-in-jetpack-compose-bbf5991cfd14",
    sourceUrl = "https://www.msn.com/en-in/money/news/budget-2024-income-tax-expectations-from-finance-minister-nirmala-sitharaman/ar-AATQ6Zz",
    sourceName = "Added"
)