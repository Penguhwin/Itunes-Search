package com.penguin.musicinfo.network.response

import com.squareup.moshi.Json

data class SearchResultResponse(
    val artistName: String,
    val artistId: String,
    val artistLinkUrl: String?,
    @Json(name = "artworkUrl100")
    val imageCoverUrl: String?,
    val previewUrl: String?,
    val trackName: String?,
    val trackId: Int?,
    val collectionName: String?,
    val collectionId: String?,
    val releaseDate: String?,
    val primaryGenreName: String?,
    val trackPrice: Double?,
    val wrapperType: String?,
    val trackTimeMillis: Long?,
    val trackExplicitness: String?
)