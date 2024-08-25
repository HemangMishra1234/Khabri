package com.project.khabri.domain

sealed interface APIError

data object Offline: APIError