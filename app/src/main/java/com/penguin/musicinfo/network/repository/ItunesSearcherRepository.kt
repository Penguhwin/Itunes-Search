package com.penguin.musicinfo.network.repository

import androidx.paging.PagingData
import com.penguin.musicinfo.network.api.ENTITY
import com.penguin.musicinfo.network.api.MEDIA_TYPE
import com.penguin.musicinfo.network.data.model.AlbumDataModel
import com.penguin.musicinfo.network.data.model.SongDataModel
import kotlinx.coroutines.flow.Flow

interface ItunesSearcherRepository
{
    suspend fun getSearchResults(
        searchTerm: String,
        mediaType: MEDIA_TYPE,
        entity: ENTITY
    ) : Flow<PagingData<SongDataModel>>

    suspend fun getSongsInTheAlbum(
        collectionId: String,
        mediaType: MEDIA_TYPE,
        entity: ENTITY
    ): List<SongDataModel>

    suspend fun getAlbumDetail(
        collectionId: String,
        mediaType: MEDIA_TYPE,
        entity: ENTITY
    ): AlbumDataModel
}