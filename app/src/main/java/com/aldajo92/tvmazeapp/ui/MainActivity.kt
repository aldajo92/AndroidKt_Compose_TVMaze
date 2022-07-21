package com.aldajo92.tvmazeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aldajo92.tvmazeapp.ui.compose_utils.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                ComposeApp()
            }
        }
    }

    @Composable
    fun ComposeApp() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.SHOW_LIST
        ) {
            composable(
                route = Route.SHOW_LIST
            ) { backStackEntry ->
                TVShowListScreen(
                    onItemClicked = { showId ->
                        // In order to discard duplicated navigation events, we check the Lifecycle
                        if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                            navController.navigate("${Route.DETAIL}/$showId")
                        }
                    }
                )
            }
            composable(
                route = "${Route.DETAIL}/{${NAVIGATION_SHOW_ID_ARGUMENT}}",
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

    object Route {
        const val SHOW_LIST = "show_list"
        const val DETAIL = "detail"
    }

}
