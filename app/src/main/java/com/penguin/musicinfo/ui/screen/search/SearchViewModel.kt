package com.penguin.musicinfo.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.penguin.musicinfo.network.api.ENTITY
import com.penguin.musicinfo.network.api.MEDIA_TYPE
import com.penguin.musicinfo.network.data.model.SongDataModel
import com.penguin.musicinfo.network.repository.ItunesSearcherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: ItunesSearcherRepository
) : ViewModel() {
    var searchTerm = mutableStateOf("")
    var isSearchingState = mutableStateOf(false)

    private var _songSearch = mutableStateOf<Flow<PagingData<SongDataModel>>>(emptyFlow())
    val songSearchState: State<Flow<PagingData<SongDataModel>>> = _songSearch

    private var _artistSearch = mutableStateOf<Flow<PagingData<SongDataModel>>>(emptyFlow())
    val artistsSearchState: State<Flow<PagingData<SongDataModel>>> = _artistSearch

    fun onSearchAction() {
        if (searchTerm.value.isNotEmpty()) {
            isSearchingState.value = true

            viewModelScope.launch {
                _songSearch.value = repo.getSearchResults(
                    searchTerm = searchTerm.value,
                    mediaType = MEDIA_TYPE.MUSIC,
                    ENTITY.SONG
                ).map { result ->
                    result
                }.cachedIn(viewModelScope)

                // Unfortunately MUSIC_ARTIST ENTITY does not provide an artwork, only an Artist URL
                // Will require another call to apple music API to grab Artwork (skipping for now)
                _artistSearch.value = repo.getSearchResults(
                    searchTerm = searchTerm.value,
                    mediaType = MEDIA_TYPE.MUSIC,
                    ENTITY.MUSIC_ARTIST
                ).map { result ->
                    result
                }.cachedIn(viewModelScope)
            }
        }
    }
}
