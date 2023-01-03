package com.penguin.musicinfo.network.api

import com.penguin.musicinfo.network.response.ApiResponse
import com.penguin.musicinfo.network.response.LookupResponse
import com.penguin.musicinfo.network.response.SearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesAPIService
{
    @GET("search")
    suspend fun getSearchResults(
        @Query("term") searchTerm: String,
        @Query("media") mediaType: String,
        @Query("entity") entity: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<SearchResultResponse>

    @GET("lookup")
    suspend fun getLookupResult(
        @Query("id") searchTerm: String,
        @Query("entity") entity: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<LookupResponse>
}