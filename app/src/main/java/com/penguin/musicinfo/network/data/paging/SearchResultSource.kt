package com.penguin.musicinfo.network.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.penguin.musicinfo.network.api.ENTITY
import com.penguin.musicinfo.network.api.MEDIA_TYPE
import com.penguin.musicinfo.network.data.mappers.songDataModel
import com.penguin.musicinfo.network.data.model.SongDataModel
import com.penguin.musicinfo.network.usecases.GetItunesMusicUseCase
import retrofit2.HttpException
import java.io.IOException

class SearchResultSource(private val itunesMusicSearch: GetItunesMusicUseCase,
                         private val searchTerm: String,
                         private val mediaType: MEDIA_TYPE,
                         private val entity: ENTITY) :
    PagingSource<Int, SongDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, SongDataModel>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SongDataModel> {
        return try {
            val page = params.key ?: 1

            val response = itunesMusicSearch.invoke(
                    searchTerm = searchTerm,
                    mediaType = mediaType.type,
                    entity = entity.type,
                    page = page
                )

            val data = response.results.map { it.songDataModel() }

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
