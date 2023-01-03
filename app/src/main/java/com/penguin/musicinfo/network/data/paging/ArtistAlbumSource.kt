package com.penguin.musicinfo.network.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.penguin.musicinfo.network.data.mappers.albumDataModel
import com.penguin.musicinfo.network.data.model.AlbumDataModel
import com.penguin.musicinfo.network.usecases.GetItunesLookupUseCase
import retrofit2.HttpException
import java.io.IOException

class ArtistAlbumSource(private val itunesArtistUseCase: GetItunesLookupUseCase,
                        private val artistId: String) :
    PagingSource<Int, AlbumDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, AlbumDataModel>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumDataModel> {
        return try {
            val page = params.key ?: 1

            val response =
                itunesArtistUseCase.invoke(
                    id = artistId,
                    entity = "Album",
                    page = page
                )

            val data = response.results.filter {
                it.wrapperType.equals(COLLECTION_WRAPPER_TYPE)
            }
                .map {
                it.albumDataModel()
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

    companion object {
        private const val COLLECTION_WRAPPER_TYPE = "collection"
    }
}
