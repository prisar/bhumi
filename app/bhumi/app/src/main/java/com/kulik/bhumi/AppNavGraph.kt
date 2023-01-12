package com.kulik.bhumi

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object About : Screen("about", R.string.about)
    object Main : Screen("main", R.string.main)
}

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier
) {

    val items = listOf(
        Screen.Main,
        Screen.About,
    )

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.Main.route, Modifier.padding(innerPadding)) {
            composable(Screen.Main.route) { Home()  }
            composable(Screen.About.route) { AboutScreen(
                onNavigateToAbout = { navController.navigate("About") },
                /*...*/
            ) }

        }
    }
}

@Composable
fun AboutScreen(
    onNavigateToAbout: () -> Unit,
    /*...*/
) {
    /*...*/
    About()
}

object Destinations {
    const val BASICS_START = "Main"
}

