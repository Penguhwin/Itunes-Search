package com.penguin.musicinfo.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.penguin.musicinfo.ui.nav.Screen

@Composable
fun AppBottomNav(modifier: Modifier,
                 navController: NavController,
                 screen: Screen) {
    BottomNavigation(modifier = modifier) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Search,
            contentDescription = "SearchScreen") },
            label = { Text("Search") },
            selected = screen == Screen.Search,
            onClick = { navController.navigate(Screen.Search.route) {
            }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Favorite,
                contentDescription = "Favorite") },
            label = { Text("Favorite") },
            selected = screen == Screen.Favorite,
            onClick = { }
        )
    }
}

@Composable
@Preview
fun PreviewAppBottomNav() {
    val navController = rememberNavController()
    AppBottomNav(modifier = Modifier, navController = navController, screen = Screen.Search)
}

