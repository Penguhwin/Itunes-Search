package com.penguin.musicinfo.network.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.penguin.musicinfo.network.data.mappers.artistInfoModel
import com.penguin.musicinfo.network.data.model.ArtistInfoModel
import com.penguin.musicinfo.network.usecases.GetItunesLookupUseCase
import retrofit2.HttpException
import java.io.IOException

class ArtistInfoSource(private val itunesArtistUseCase: GetItunesLookupUseCase,
                       private val artistId: String) :
    PagingSource<Int, ArtistInfoModel>() {

    override fun getRefreshKey(state: PagingState<Int, ArtistInfoModel>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtistInfoModel> {
        return try {
            val page = params.key ?: 1

            val response =
                itunesArtistUseCase.invoke(
                    id = artistId,
                    entity = "info",
                    page = page
                )

            val data = response.results
                .map {
                    it.artistInfoModel()
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
