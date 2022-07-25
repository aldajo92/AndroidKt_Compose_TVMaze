package com.aldajo92.tvmazeapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home : BottomBarScreen(
        route = "MAIN",
        title = "Main",
        icon = Icons.Default.Home
    )

    object Search : BottomBarScreen(
        route = "SEARCH",
        title = "Search",
        icon = Icons.Default.Search
    )

    object Favorite : BottomBarScreen(
        route = "FAVORITE",
        title = "Favorite",
        icon = Icons.Default.Favorite
    )

}
