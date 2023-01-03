package com.penguin.musicinfo.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.penguin.musicinfo.network.api.ENTITY
import com.penguin.musicinfo.network.api.MEDIA_TYPE
import com.penguin.musicinfo.network.data.mappers.albumDataModel
import com.penguin.musicinfo.network.data.mappers.songDataModel
import com.penguin.musicinfo.network.data.model.AlbumDataModel
import com.penguin.musicinfo.network.data.model.SongDataModel
import com.penguin.musicinfo.network.data.paging.SearchResultSource
import com.penguin.musicinfo.network.usecases.GetItunesLookupUseCase
import com.penguin.musicinfo.network.usecases.GetItunesMusicUseCase
import kotlinx.coroutines.flow.Flow

class ItunesSearcherRepositoryImpl (private val itunesMusicSearch: GetItunesMusicUseCase,
                                    private val itunesLookupSearch: GetItunesLookupUseCase) : ItunesSearcherRepository {
    override suspend fun getSearchResults(
        searchTerm: String,
        mediaType: MEDIA_TYPE,
        entity: ENTITY
    ): Flow<PagingData<SongDataModel>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = true, pageSize = 20),
            pagingSourceFactory = {
                SearchResultSource(
                    itunesMusicSearch,
                    searchTerm = searchTerm,
                    mediaType = mediaType,
                    entity = entity
                )
            }
        ).flow
    }

    override suspend fun getSongsInTheAlbum(
        collectionId: String,
        mediaType: MEDIA_TYPE,
        entity: ENTITY
    ): List<SongDataModel> {
        val response = itunesLookupSearch.invoke(collectionId, entity = entity.type, 0, 100)
        return response.results.map {
            it.songDataModel()
        }
    }

    override suspend fun getAlbumDetail(
        collectionId: String,
        mediaType: MEDIA_TYPE,
        entity: ENTITY
    ): AlbumDataModel {
        val response = itunesLookupSearch.invoke(collectionId, entity = entity.type, 0, 100)
        return response.results.firstOrNull()?.albumDataModel() ?: AlbumDataModel(
            "",
            "",
            "",
            "",
            0,
            "",
            ""
        )
    }
}