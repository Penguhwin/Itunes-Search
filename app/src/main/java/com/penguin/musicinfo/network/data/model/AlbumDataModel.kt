package com.penguin.musicinfo.network.data.model

data class AlbumDataModel(
    override val imageUrl: String,
    override val artistId: String,
    override val artistName: String,
    val albumName: String,
    val trackCount: Int,
    val wrapperType: String,
    val artistViewUrl: String?
) : ImgDataModel