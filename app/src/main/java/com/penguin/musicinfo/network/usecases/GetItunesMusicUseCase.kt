package com.penguin.musicinfo.network.usecases

import com.penguin.musicinfo.network.api.ItunesAPIService


class GetItunesMusicUseCase (private val api: ItunesAPIService)
{
    companion object {
        const val RESULTS_LIMIT = 20
    }

    suspend operator fun invoke
    (
        searchTerm: String,
        mediaType: String,
        entity: String,
        page: Int,
        limit: Int = RESULTS_LIMIT
    ) =
        api.getSearchResults(
            searchTerm,
            mediaType,
            entity,
            page * RESULTS_LIMIT,
            limit
        ).also {
            it.page = page
        }
}