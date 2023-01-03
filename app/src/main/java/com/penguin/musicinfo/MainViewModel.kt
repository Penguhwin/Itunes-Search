package com.penguin.musicinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(SPLASH_SCREEN_DELAY_IN_MILLIS)
            _isLoading.emit(false)
        }
    }

    companion object {
        private const val SPLASH_SCREEN_DELAY_IN_MILLIS = 2000L
    }
}