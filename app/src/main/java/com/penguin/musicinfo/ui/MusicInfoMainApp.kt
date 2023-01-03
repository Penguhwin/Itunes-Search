package com.penguin.musicinfo.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.penguin.musicinfo.ui.nav.NavigationGraph
import com.penguin.musicinfo.ui.theme.ArtistInfoTheme

@Composable
fun ArtistInfoMainApp(
    navController: NavHostController = rememberNavController()
) {
    ArtistInfoTheme {
        NavigationGraph(navController = navController)
    }
}