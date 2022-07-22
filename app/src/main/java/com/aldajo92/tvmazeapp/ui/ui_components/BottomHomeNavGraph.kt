package com.aldajo92.tvmazeapp.ui.ui_components

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aldajo92.tvmazeapp.ui.screens.MAIN_ROUTE_DETAIL
import com.aldajo92.tvmazeapp.ui.screens.home.SectionTVShowList
import com.aldajo92.tvmazeapp.ui.screens.home.SearchScreen

@Composable
fun BottomHomeNavGraph(
    navMainController: NavHostController,
    navHomeController: NavHostController
) {
    NavHost(
        navController = navHomeController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) { backStackEntry ->
            SectionTVShowList(
                onItemClicked = { showId ->
                    // In order to discard duplicated navigation events, we check the Lifecycle
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navMainController.navigate("${MAIN_ROUTE_DETAIL}/$showId")
                    }
                }
            )
        }
        composable(route = BottomBarScreen.Search.route) {
            SearchScreen()
        }
    }
}