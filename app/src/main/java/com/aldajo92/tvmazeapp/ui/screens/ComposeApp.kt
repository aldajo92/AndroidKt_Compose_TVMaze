package com.aldajo92.tvmazeapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aldajo92.tvmazeapp.ui.NAVIGATION_SHOW_ID_ARGUMENT
import com.aldajo92.tvmazeapp.ui.screens.detail.DetailsScreen
import com.aldajo92.tvmazeapp.ui.screens.home.HomeScreen

@Composable
fun ComposeApp() {
    val navMainController = rememberNavController()
    NavHost(
        navController = navMainController,
        startDestination = MAIN_ROUTE_HOME
    ) {
        composable(
            route = MAIN_ROUTE_HOME
        ) {
            HomeScreen(navMainController)
        }
        composable(
            route = "${MAIN_ROUTE_DETAIL}/{${NAVIGATION_SHOW_ID_ARGUMENT}}",
            arguments = listOf(
                navArgument(NAVIGATION_SHOW_ID_ARGUMENT) {
                    type = NavType.StringType
                }
            ),
        ) {
            val showID = (it.arguments?.get(NAVIGATION_SHOW_ID_ARGUMENT) as String?).orEmpty()
            DetailsScreen(showID)
        }
    }
}

const val MAIN_ROUTE_HOME = "home_screen"
const val MAIN_ROUTE_DETAIL = "detail_screen"
