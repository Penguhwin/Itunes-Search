package com.penguin.musicinfo.network.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.penguin.musicinfo.network.api.ENTITY
import com.penguin.musicinfo.network.api.MEDIA_TYPE
import com.penguin.musicinfo.network.data.mappers.songDataModel
import com.penguin.musicinfo.network.data.model.SongDataModel
import com.penguin.musicinfo.network.usecases.GetItunesLookupUseCase
import retrofit2.HttpException
import java.io.IOException

class AlbumDetailSource(private val itunesArtistUseCase: GetItunesLookupUseCase,
                        private val collectionId: String,
                        private val mediaType: MEDIA_TYPE,
                        private val entity: ENTITY,
                        private val pageSize: Int
) :
    PagingSource<Int, SongDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, SongDataModel>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SongDataModel> {
        return try {
            val page = params.key ?: 1

            val response =
                itunesArtistUseCase.invoke(
                    id = collectionId,
                    entity = entity.type,
                    page = page,
                    limit = pageSize
                )

            val data = response.results
                .map {
                    it.songDataModel()
                }

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else response.page?.plus(1)
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}
