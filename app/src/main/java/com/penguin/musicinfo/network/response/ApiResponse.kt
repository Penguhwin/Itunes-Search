package com.penguin.musicinfo.network.response

data class ApiResponse<T>(
    val resultCount: Int,
    val results: List<T>,
    // Optional value, temporarily way of converting an offset -> pagination
    var page: Int?
)