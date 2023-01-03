package com.penguin.musicinfo.network.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface AppleMusicService
{
    @GET
    suspend fun getData(
        @Url url: String,
    ): ResponseBody
}