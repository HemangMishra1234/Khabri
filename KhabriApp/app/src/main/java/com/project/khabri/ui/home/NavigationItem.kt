package com.project.khabri.ui.home

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationItem(
    val displayName: String,
    val iconSelected: ImageVector,
    val iconNotSelected: ImageVector
) {
    SHORTS("Shorts", Icons.Default.Newspaper, Icons.Outlined.Newspaper),
    HOME(
        displayName = "Home",
        iconSelected = Icons.Default.Home,
        iconNotSelected = Icons.Outlined.Home
    ),
    PROFILE("Profile", Icons.Default.Person, Icons.Outlined.Person),
    ADDNEWS("Post News", Icons.Default.Add, Icons.Outlined.Add)

}