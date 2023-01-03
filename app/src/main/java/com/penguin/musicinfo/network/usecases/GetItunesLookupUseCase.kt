package com.penguin.musicinfo.network.usecases

import androidx.paging.DEBUG
import androidx.paging.LOGGER
import com.penguin.musicinfo.network.api.ItunesAPIService
import com.penguin.musicinfo.network.response.ApiResponse


class GetItunesLookupUseCase (private val api: ItunesAPIService)
{
    companion object {
        const val RESULTS_LIMIT = 20
    }

    suspend operator fun invoke
    (
        id: String,
        entity: String,
        page: Int,
        limit: Int = RESULTS_LIMIT
    ) =
        try {
            api.getLookupResult(
                id,
                entity,
                page * RESULTS_LIMIT,
                limit
            ).also {
                // Assigning pages attribute for pagination
                it.page = page

                // Found out the API doesn't support pagination via limit/offset parameters
            }
        } catch (e: Exception) {
            LOGGER?.log(DEBUG, e.stackTraceToString())
            ApiResponse(0, listOf(), page)
        }
}