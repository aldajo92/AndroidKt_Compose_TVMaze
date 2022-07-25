package com.aldajo92.tvmazeapp.mappers

import com.aldajo92.tvmazeapp.presentation.ShowRequestStatus
import com.aldajo92.tvmazeapp.ui.models.SearchResultUIEvents

fun ShowRequestStatus.toUIEvent(): SearchResultUIEvents = when (this) {
    is ShowRequestStatus.OnError -> SearchResultUIEvents.OnError(this.errorMessage)
    is ShowRequestStatus.OnLoading -> SearchResultUIEvents.OnLoading
    is ShowRequestStatus.OnSuccess -> SearchResultUIEvents.OnSuccess(
        this.list.map { it.toUIModel() }
    )
}
