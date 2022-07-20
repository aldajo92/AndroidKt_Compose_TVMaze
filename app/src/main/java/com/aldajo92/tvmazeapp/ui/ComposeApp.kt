package com.aldajo92.tvmazeapp.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.SHOW_LIST
    ) {
        composable(Route.SHOW_LIST) { backStackEntry ->
            ShowsScreen(
                onItemClicked = { showId ->
                    // In order to discard duplicated navigation events, we check the Lifecycle
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Route.DETAIL}/$showId")
                    }
                }
            )
        }
        composable(
            route = "${Route.DETAIL}/{${Argument.USERNAME}}",
            arguments = listOf(
                navArgument(Argument.USERNAME) {
                    type = NavType.StringType
                }
            ),
        ) {
            DetailsScreen()
        }
    }
}

object Route {
    const val SHOW_LIST = "show_list"
    const val DETAIL = "detail"
}

object Argument {
    const val USERNAME = "username"
}
