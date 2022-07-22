package com.aldajo92.tvmazeapp.ui.ui_components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aldajo92.tvmazeapp.ui.BottomBarScreen
import com.aldajo92.tvmazeapp.ui.screens.main.HomeScreen
import com.aldajo92.tvmazeapp.ui.screens.main.SearchScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Search.route) {
            SearchScreen()
        }
    }
}