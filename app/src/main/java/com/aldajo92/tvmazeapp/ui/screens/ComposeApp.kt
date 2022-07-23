package com.aldajo92.tvmazeapp.ui.screens

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.aldajo92.tvmazeapp.ui.NAVIGATION_SHOW_ID_ARGUMENT
import com.aldajo92.tvmazeapp.ui.screens.detail.DetailsScreen
import com.aldajo92.tvmazeapp.ui.screens.home.HomeScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
@Composable
fun ComposeApp() {
    val navMainController = rememberAnimatedNavController()
    AnimatedNavHost(
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
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            arguments = listOf(
                navArgument(NAVIGATION_SHOW_ID_ARGUMENT) {
                    type = NavType.StringType
                }
            ),
        ) {
            val showID = (it.arguments?.get(NAVIGATION_SHOW_ID_ARGUMENT) as String?).orEmpty()
            DetailsScreen(
                showID
            ) {
                navMainController.navigateUp()
            }
        }
    }
}

const val MAIN_ROUTE_HOME = "home_screen"
const val MAIN_ROUTE_DETAIL = "detail_screen"
