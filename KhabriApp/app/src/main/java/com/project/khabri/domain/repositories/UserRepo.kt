package com.project.khabri.domain.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.project.khabri.domain.data.UserData
import com.project.khabri.domain.remote.NewsAPI
import retrofit2.HttpException

class UserRepo(val auth: FirebaseAuth, val api: NewsAPI) {
    suspend fun pushUserData() {
        val email = auth.currentUser?.email
        val name = auth.currentUser?.displayName
        val id = auth.currentUser?.uid
        if (email == null || name == null || id == null) {
            Log.i("error", "user not logged in")
            return
        }
        val response = api.updateUserData(
            UserData(
                email = email,
                name = name,
                id = id
            )
        )
        Log.i("response", "$response")
        if (response.isSuccessful.not()) {
            throw HttpException(response)
        }
        val articleList = response.body() ?: throw Exception()
        Log.i("fetched", "$articleList")
    }
}