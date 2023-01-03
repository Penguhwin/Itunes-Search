package com.penguin.musicinfo.ui.nav

sealed class Screen(val route: String) {
    object Search: Screen(route = "search_screen")
    object SongDetail: Screen(route = "song_detail_screen")
    object Favorite: Screen(route = "favorite_screen")
}