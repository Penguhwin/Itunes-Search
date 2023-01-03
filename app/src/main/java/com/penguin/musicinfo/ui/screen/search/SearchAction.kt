package com.penguin.musicinfo.ui.screen.search

sealed interface SearchAction {
    data class QueryChange(val query: String = "") : SearchAction
    data class AddError(val error: Throwable, val onRetry: () -> Unit) : SearchAction
    object Search : SearchAction
}