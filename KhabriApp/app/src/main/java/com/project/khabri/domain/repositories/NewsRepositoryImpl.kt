package com.project.khabri.domain.repositories

import android.util.Log
import com.project.khabri.data.remote.New
import com.project.khabri.domain.APIResponse
import com.project.khabri.domain.data.Article
import com.project.khabri.domain.data.NewsCategories
import com.project.khabri.domain.remote.NewsAPI
import com.project.khabri.domain.repositories.NewsRepository
import com.project.khabri.domain.ArticlesError
import retrofit2.HttpException

class NewsRepositoryImpl(private val api: NewsAPI) : NewsRepository {

    //        override suspend fun getNews(categories: NewsCategories, query: String?): Resource<List<Article>,ArticlesError> {
//        return try {
//            delay(3000)
////            throw Exception()
//            Resource.Success(listOf(article, article, article, article, article, article))
//        }
//        catch (e: Exception){
//            Resource.Error(ArticlesError.NetworkError.NO_INTERNET)
//        }
//    }
    suspend fun getNews(categories: NewsCategories): APIResponse<List<Article>, ArticlesError> {
        return try {
            val response = api.getHealthNews()
            Log.i("response", "$response, ${response.body()}")
            if (response.isSuccessful.not()) {
                throw HttpException(response)
            }
            val articleList = response.body()?.news?.map { it.toArticle() } ?: throw Exception()
            Log.i("fetched", "$articleList")
            APIResponse.Success(articleList)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
            } else if (e.code() == 408) {
                APIResponse.Error(ArticlesError.NetworkError.TIMEOUT)
            } else if (e.code() == 500) {
                APIResponse.Error(ArticlesError.NetworkError.SERVER_NOT_RESPONDING)
            } else {
                APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
            }
        } catch (e: Exception) {
            Log.i("hi", "h $e")
            APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
        }
    }

    override suspend fun getSportsNews(): APIResponse<List<Article>, ArticlesError> {
        return try {
            val response = api.getSportsNews()
            Log.i("response", "$response, ${response.body()}")
            if (response.isSuccessful.not()) {
                throw HttpException(response)
            }
            val articleList = response.body()?.news?.map { it.toArticle() } ?: throw Exception()
            Log.i("fetched", "$articleList")
            APIResponse.Success(articleList)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
            } else if (e.code() == 408) {
                APIResponse.Error(ArticlesError.NetworkError.TIMEOUT)
            } else if (e.code() == 500) {
                APIResponse.Error(ArticlesError.NetworkError.SERVER_NOT_RESPONDING)
            } else {
                APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
            }
        } catch (e: Exception) {
            Log.i("hi", "h $e")
            APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
        }
    }

    override suspend fun getHealthNews(

    ): APIResponse<List<Article>, ArticlesError> {
        return try {
            val response = api.getHealthNews()
            Log.i("response", "$response, ${response.body()}")
            if (response.isSuccessful.not()) {
                throw HttpException(response)
            }
            val articleList = response.body()?.news?.map { it.toArticle() } ?: throw Exception()
            Log.i("fetched", "$articleList")
            APIResponse.Success(articleList)
        } catch (e: HttpException) {
            Log.i("hi", "h$e")
            if (e.code() == 401) {
                APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
            } else if (e.code() == 408) {
                APIResponse.Error(ArticlesError.NetworkError.TIMEOUT)
            } else if (e.code() == 500) {
                APIResponse.Error(ArticlesError.NetworkError.SERVER_NOT_RESPONDING)
            } else {
                APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
            }
        } catch (e: Exception) {
            Log.i("hi", "h")
            APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
        }
    }

    override suspend fun getBusinessNews(

    ): APIResponse<List<Article>, ArticlesError> {
        return try {
            val response = api.getBusinessNews()
            Log.i("response", "$response, ${response.body()}")
            if (response.isSuccessful.not()) {
                throw HttpException(response)
            }
            val articleList = response.body()?.news?.map { it.toArticle() } ?: throw Exception()
            Log.i("fetched", "$articleList")
            APIResponse.Success(articleList)
        } catch (e: HttpException) {
            Log.i("hi", "h $e")
            if (e.code() == 401) {
                APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
            } else if (e.code() == 408) {
                APIResponse.Error(ArticlesError.NetworkError.TIMEOUT)
            } else if (e.code() == 500) {
                APIResponse.Error(ArticlesError.NetworkError.SERVER_NOT_RESPONDING)
            } else {
                APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
            }
        } catch (e: Exception) {
            Log.i("hi", "h $e")
            APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
        }
    }

    override suspend fun getEntertainmentNews(

    ): APIResponse<List<Article>, ArticlesError> {
        return try {
            val response = api.getEntertainmentNews()
            Log.i("response", "$response, ${response.body()}")
            if (response.isSuccessful.not()) {
                throw HttpException(response)
            }
            val articleList = response.body()?.news?.map { it.toArticle() } ?: throw Exception()
            Log.i("fetched", "$articleList")
            APIResponse.Success(articleList)
        } catch (e: HttpException) {
            Log.i("hi", "h $e")
            if (e.code() == 401) {
                APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
            } else if (e.code() == 408) {
                APIResponse.Error(ArticlesError.NetworkError.TIMEOUT)
            } else if (e.code() == 500) {
                APIResponse.Error(ArticlesError.NetworkError.SERVER_NOT_RESPONDING)
            } else {
                APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
            }
        } catch (e: Exception) {
            Log.i("hi", "h $e")
            APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
        }
    }

    override suspend fun getTechnologyNews(

    ): APIResponse<List<Article>, ArticlesError> {
        return try {

            val response = api.getTechnologyNews()
            Log.i("response", "$response, ${response.body()}")
            if (response.isSuccessful.not()) {
                throw HttpException(response)
            }
            val articleList = response.body()?.news?.map { it.toArticle() } ?: throw Exception()
            Log.i("fetched", "$articleList")
            APIResponse.Success(articleList)
        } catch (e: HttpException) {
            Log.i("hi", "h $e")
            if (e.code() == 401) {
                APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
            } else if (e.code() == 408) {
                APIResponse.Error(ArticlesError.NetworkError.TIMEOUT)
            } else if (e.code() == 500) {
                APIResponse.Error(ArticlesError.NetworkError.SERVER_NOT_RESPONDING)
            } else {
                APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
            }
        } catch (e: Exception) {
            Log.i("hi", "h $e")
            APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
        }
    }

    override suspend fun getRecommendedNews(uid: String): APIResponse<List<Article>, ArticlesError> {
        return try {
            val response = api.getRecommendedNews("")
            Log.i("response", "$response, ${response.body()}")
            if (response.isSuccessful.not()) {
                throw HttpException(response)
            }
            val articleList = response.body()?.news?.map { it.toArticle() } ?: throw Exception()
            Log.i("fetched", "$articleList")
            APIResponse.Success(articleList)
        } catch (e: HttpException) {
            Log.i("hi", "h $e")
            if (e.code() == 401) {
                APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
            } else if (e.code() == 408) {
                APIResponse.Error(ArticlesError.NetworkError.TIMEOUT)
            } else if (e.code() == 500) {
                APIResponse.Error(ArticlesError.NetworkError.SERVER_NOT_RESPONDING)
            } else {
                APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
            }
        } catch (e: Exception) {
            Log.i("hi", "h $e")
            APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
        }
    }

    override suspend fun getScienceNews(): APIResponse<List<Article>, ArticlesError> {
        return try {
            val response = api.getHealthNews()
            Log.i("response", "$response, ${response.body()}")
            if (response.isSuccessful.not()) {
                throw HttpException(response)
            }
            val articleList = response.body()?.news?.map { it.toArticle() } ?: throw Exception()
            Log.i("fetched", "$articleList")
            APIResponse.Success(articleList)
        } catch (e: HttpException) {
            Log.i("hi", "h $e")
            if (e.code() == 401) {
                APIResponse.Error(ArticlesError.NetworkError.NO_INTERNET)
            } else if (e.code() == 408) {
                APIResponse.Error(ArticlesError.NetworkError.TIMEOUT)
            } else if (e.code() == 500) {
                APIResponse.Error(ArticlesError.NetworkError.SERVER_NOT_RESPONDING)
            } else {
                APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
            }
        } catch (e: Exception) {
            Log.i("hi", "h $e")
            APIResponse.Error(ArticlesError.NetworkError.UNKNOWN)
        }
    }
}

fun New.toArticle(): Article {
    return Article(
        unique_id = this.unique_id,
        content = this.content,
        description = this.description,
        category = this.category,
        image = this.image,
        published_at = this.published_at,
        source_name = this.source_name,
        source_url = this.source_url,
        country = this.country,
        title = this.title,
        url = this.url,
        is_real = this.is_real.toFloat(),
        isLiked = false,
        isSaved = false
    )
}