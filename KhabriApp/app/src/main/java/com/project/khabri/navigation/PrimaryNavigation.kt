package com.project.pattagobhi.ui.navigation

import kotlinx.serialization.Serializable

sealed interface PrimaryNavigation {
    @Serializable
    data object AuthenticationScreen: PrimaryNavigation

    @Serializable
    data object LoadingScreen: PrimaryNavigation

    @Serializable
    data object MainScreen: PrimaryNavigation

    @Serializable
    data object OnBoardingScreen: PrimaryNavigation
}

