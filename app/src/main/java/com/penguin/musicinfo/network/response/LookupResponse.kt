package com.penguin.musicinfo.network.response

import com.squareup.moshi.Json

data class LookupResponse(
    val artistName: String,
    val artistId: String,
    val wrapperType: String?,
    @Json(name = "artworkUrl100")
    val imageCoverUrl: String?,
    val artistLinkUrl: String?,
    val primaryGenreName: String?,
    val artistViewUrl: String?,
    val trackName: String?,
    val trackCount: Int?,
    val trackId: Int?,
    val collectionName: String?,
    val collectionId: String?,
    val releaseDate: String?,
    val trackPrice: Double?,
    val trackTimeMillis: Long?,
    val trackExplicitness: String?
)