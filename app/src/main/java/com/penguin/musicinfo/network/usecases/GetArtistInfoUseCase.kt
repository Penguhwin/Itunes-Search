package com.penguin.musicinfo.network.usecases

import androidx.paging.DEBUG
import androidx.paging.LOGGER
import com.penguin.musicinfo.network.api.AppleMusicService

class GetArtistInfoUseCase (private val api: AppleMusicService) {

    suspend operator fun invoke(
        url: String
    ) =
        try {
            api.getData(url)
        } catch (e: Exception) {
            LOGGER?.log(DEBUG, e.stackTraceToString())
        }


    private fun getArtistArtworkUrl(response: String): String {
        val pattern = "meta property=\"og:image\" content=\"(.*png)\""
        val regex = Regex(pattern)
        val matchResult = regex.find(response, 100)

        if (matchResult != null) {
            val imageUrl = matchResult.groups[1]?.value
            println("Image URL: $imageUrl")
            return imageUrl ?: ""
        }
        return ""
    }

    private fun getArtistAboutMe(response: String): String {
        val pattern = "<p class=\"we-artwork__caption--overflow\">(.*?)</p>"
        val regex = Regex(pattern)
        val matchResult = regex.find(response)

        if (matchResult != null) {
            val imageUrl = matchResult.groups[1]?.value
            println("Image URL: $imageUrl")
            return imageUrl ?: ""
        }
        return ""
    }
}