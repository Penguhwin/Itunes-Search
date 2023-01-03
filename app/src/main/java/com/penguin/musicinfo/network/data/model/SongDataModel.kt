package com.penguin.musicinfo.network.data.model

data class SongDataModel(
    override val imageUrl: String = "",
    override val artistId: String = "",
    override val artistName: String = "",
    val isExplicit: Boolean = false,
    val trackLength: String = "",
    val trackName: String = "",
    val trackId: Int = 0,
    val albumName: String = "",
    val collectionId: String = "",
    val formattedDate: String = "",
    val wrapperType: String = ""
) : ImgDataModel