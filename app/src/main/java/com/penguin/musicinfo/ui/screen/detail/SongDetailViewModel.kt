package com.penguin.musicinfo.ui.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.penguin.musicinfo.network.api.ENTITY
import com.penguin.musicinfo.network.api.MEDIA_TYPE
import com.penguin.musicinfo.network.data.model.AlbumDataModel
import com.penguin.musicinfo.network.data.model.SongDataModel
import com.penguin.musicinfo.network.repository.ItunesSearcherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: ItunesSearcherRepository
) : ViewModel() {

    private val initialArtistId: String = checkNotNull(savedStateHandle["collectionId"])

    private var _songsInAlbum = mutableStateOf<List<SongDataModel>>(listOf())
    val songsInAlbumState: State<List<SongDataModel>> = _songsInAlbum

    private var _artistInfo = mutableStateOf(AlbumDataModel("", "", "", "", 0, "", ""))
    val artistInfoState: State<AlbumDataModel> = _artistInfo

    var collectionId = mutableStateOf(initialArtistId)

    private fun getAlbumDetails() {
        viewModelScope.launch {
            val response = repo.getSongsInTheAlbum(
                collectionId = collectionId.value,
                mediaType = MEDIA_TYPE.MUSIC,
                entity = ENTITY.SONG
            )

            _songsInAlbum.value = response.filter { it.wrapperType == TRACK_WRAPPER_TYPE }

            _artistInfo.value = repo.getAlbumDetail(
                collectionId = collectionId.value,
                mediaType = MEDIA_TYPE.MUSIC,
                entity = ENTITY.SONG
            )
        }
    }

    init {
        getAlbumDetails()
    }

    companion object {
        private const val TRACK_WRAPPER_TYPE = "track"
    }
}