package com.penguin.musicinfo.network.data.mappers

import com.penguin.musicinfo.network.data.model.*
import com.penguin.musicinfo.network.response.LookupResponse
import com.penguin.musicinfo.network.response.SearchResultResponse

fun SearchResultResponse.songDataModel(): SongDataModel {
    return SongDataModel(
        artistId = artistId,
        artistName = artistName,
        imageUrl = imageCoverUrl ?: "",
        trackName = trackName ?: "",
        trackId = trackId ?: -1,
        albumName = collectionName ?: "",
        formattedDate = releaseDate ?: "",
        wrapperType = wrapperType ?: "",
        isExplicit = trackExplicitness?.equals("explicit") ?: false,
        collectionId = collectionId ?: "",
        trackLength = millisToMinutes(trackTimeMillis ?: 0L)
    )
}

fun LookupResponse.albumDataModel(): AlbumDataModel {
    return AlbumDataModel(
        artistId = artistId,
        artistName = artistName,
        imageUrl = imageCoverUrl ?: "",
        albumName = collectionName ?: "",
        wrapperType = wrapperType ?: "",
        artistViewUrl = artistViewUrl ?: "",
        trackCount = trackCount ?: 0
    )
}

fun LookupResponse.artistInfoModel(): ArtistInfoModel {
    return ArtistInfoModel(
        artistId = artistId,
        artistName = artistName,
        primaryGenreName = primaryGenreName ?: "",
        artistLinkUrl = artistLinkUrl ?: ""
    )
}

fun LookupResponse.songDataModel(): SongDataModel {
    return SongDataModel(
        artistId = artistId,
        artistName = artistName,
        imageUrl = imageCoverUrl ?: "",
        trackName = trackName ?: "",
        trackId = trackId ?: -1,
        albumName = collectionName ?: "",
        formattedDate = releaseDate ?: "",
        wrapperType = wrapperType ?: "",
        collectionId = collectionId ?: "",
        isExplicit = trackExplicitness?.equals("explicit") ?: false,
        trackLength = millisToMinutes(trackTimeMillis ?: 0L)
    )
}


fun millisToMinutes(millis: Long): String {
    if (millis == 0L) {
        return ""
    }
    val minutes = millis / 1000 / 60
    val seconds = millis / 1000 % 60
    return "$minutes:$seconds"
}