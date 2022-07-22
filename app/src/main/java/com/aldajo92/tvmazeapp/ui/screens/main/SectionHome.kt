package com.aldajo92.tvmazeapp.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aldajo92.tvmazeapp.ui.screens.detail.DetailsScreen
import com.aldajo92.tvmazeapp.ui.MainActivity
import com.aldajo92.tvmazeapp.ui.NAVIGATION_SHOW_ID_ARGUMENT
import com.aldajo92.tvmazeapp.ui.TVShowListScreen

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainActivity.Route.SHOW_LIST
    ) {
        composable(
            route = MainActivity.Route.SHOW_LIST
        ) { backStackEntry ->
            TVShowListScreen(
                onItemClicked = { showId ->
                    // In order to discard duplicated navigation events, we check the Lifecycle
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${MainActivity.Route.DETAIL}/$showId")
                    }
                }
            )
        }
        composable(
            route = "${MainActivity.Route.DETAIL}/{${NAVIGATION_SHOW_ID_ARGUMENT}}",
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
