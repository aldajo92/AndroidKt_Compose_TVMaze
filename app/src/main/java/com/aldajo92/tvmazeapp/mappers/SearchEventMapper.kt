package com.aldajo92.tvmazeapp.mappers

import com.aldajo92.tvmazeapp.presentation.SearchResultStatus
import com.aldajo92.tvmazeapp.ui.models.SearchResultUIEvents

fun SearchResultStatus.toUIEvent(): SearchResultUIEvents = when (this) {
    is SearchResultStatus.OnError -> SearchResultUIEvents.OnError(this.errorMessage)
    is SearchResultStatus.OnLoading -> SearchResultUIEvents.OnLoading
    is SearchResultStatus.OnSuccess -> SearchResultUIEvents.OnSuccess(
        this.list.map { it.show.toUIModel() }
    )
}