package com.penguin.musicinfo.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.penguin.musicinfo.ui.screen.detail.SongDetailScreen
import com.penguin.musicinfo.ui.screen.search.SearchScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Search.route
    ) {
        composable(
            Screen.Search.route
        ) {
            SearchScreen(navController = navController)
        }

        composable(
            "${Screen.SongDetail.route}/{collectionId}",
            arguments = listOf(navArgument("collectionId") { type = NavType.StringType })
        ) {
            SongDetailScreen(navController = navController)
        }
    }
}