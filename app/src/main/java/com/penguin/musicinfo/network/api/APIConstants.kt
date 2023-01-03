package com.penguin.musicinfo.network.api

public class APIConstants {
    companion object {
        val MEDIA_TYPE_TO_MEDIA_MAPPING: Map<MEDIA_TYPE, Set<ENTITY>> = mapOf(
            MEDIA_TYPE.MUSIC to setOf(ENTITY.SONG, ENTITY.ALBUM, ENTITY.MUSIC_VIDEO),
            MEDIA_TYPE.MUSIC_VIDEO to setOf(ENTITY.MUSIC_ARTIST, ENTITY.MUSIC_VIDEO),
        )
    }
}

enum class MEDIA_TYPE(val type: String) {
    MUSIC("music"),
    MUSIC_VIDEO("musicVideo")
}

enum class ENTITY(val type: String) {
    SONG("song"),
    ALBUM("album"),
    MUSIC_VIDEO("musicVideo"),
    MUSIC_ARTIST("musicArtist"),
    MOVIE("movie")
}