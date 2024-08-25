package com.project.khabri.domain

sealed interface ArticlesError: APIError {
    enum class NetworkError: ArticlesError {
        NO_INTERNET, TIMEOUT, SERVER_NOT_RESPONDING, UNKNOWN
    }
    enum class LocalError: ArticlesError {

    }
    object NOT_FOUND: ArticlesError
}