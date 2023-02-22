package com.aldajo92.tvmazeapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aldajo92.tvmazeapp.ui.BottomBarScreen
import com.aldajo92.tvmazeapp.ui.screens.MAIN_ROUTE_DETAIL

@Composable
fun HomeScreen(navMainController: NavHostController) {
    val navHomeController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navHomeController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomHomeNavGraph(
                navHomeController = navHomeController,
                navMainController = navMainController
            )
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Search,
        BottomBarScreen.Favorite
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Column {
        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            screens.forEach {
                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .padding(horizontal = 2.dp)
                        .weight(1f)
                        .background(if (currentDestination?.route == it.route) Color.White else Color.Transparent)
                )
            }
        }
        BottomNavigation {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

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
            SectionTVShowList { showId ->
                // In order to discard duplicated navigation events, we check the Lifecycle
                if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                    navMainController.navigate("$MAIN_ROUTE_DETAIL/$showId")
                }
            }
        }
        composable(route = BottomBarScreen.Search.route) { backStackEntry ->
            SectionSearch { showId ->
                // In order to discard duplicated navigation events, we check the Lifecycle
                if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                    navMainController.navigate("$MAIN_ROUTE_DETAIL/$showId")
                }
            }
        }
        composable(route = BottomBarScreen.Favorite.route) { backStackEntry ->
            SectionFavorite { showId ->
                // In order to discard duplicated navigation events, we check the Lifecycle
                if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                    navMainController.navigate("$MAIN_ROUTE_DETAIL/$showId")
                }
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(label = {
        Text(text = screen.title)
    },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = ""
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route)
        }
    )
}
