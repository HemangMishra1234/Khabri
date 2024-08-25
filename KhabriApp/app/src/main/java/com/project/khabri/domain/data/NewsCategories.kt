package com.project.khabri.domain.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.SportsCricket
import androidx.compose.ui.graphics.vector.ImageVector

enum class NewsCategories(val value: String, val displayName: String, val icon: ImageVector) {
    BUSINESS("business", "Business", Icons.Default.CurrencyBitcoin),
    ENTERTAINMENT("entertainment","Entertainment", Icons.Default.Movie),
    HEALTH("health", "Health", Icons.Default.HealthAndSafety),
    SCIENCE("science", "Science", Icons.Default.Science),
    SPORTS("sports", "Sports", Icons.Default.SportsCricket),
    TECHNOLOGY("technology", "Technology", Icons.Default.Laptop)
}