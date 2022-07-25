package com.aldajo92.tvmazeapp.mappers

import com.aldajo92.tvmazeapp.presentation.ShowRequestStatus
import com.aldajo92.tvmazeapp.ui.models.ShowResultUIEvents

fun ShowRequestStatus.toUIEvent(): ShowResultUIEvents = when (this) {
    is ShowRequestStatus.OnError -> ShowResultUIEvents.OnError(this.errorMessage)
    is ShowRequestStatus.OnLoading -> ShowResultUIEvents.OnLoading
    is ShowRequestStatus.OnSuccess -> ShowResultUIEvents.OnSuccess(
        this.list.map { it.toUIModel() }
    )
}
