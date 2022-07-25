package com.aldajo92.tvmazeapp.mappers

import com.aldajo92.tvmazeapp.presentation.events.EpisodesRequestStatus
import com.aldajo92.tvmazeapp.presentation.events.ShowsRequestStatus
import com.aldajo92.tvmazeapp.ui.models.EpisodeResultUIEvents
import com.aldajo92.tvmazeapp.ui.models.ShowResultUIEvents

fun ShowsRequestStatus.toUIEvent(): ShowResultUIEvents = when (this) {
    is ShowsRequestStatus.OnError -> ShowResultUIEvents.OnError(this.errorMessage)
    is ShowsRequestStatus.OnLoading -> ShowResultUIEvents.OnLoading
    is ShowsRequestStatus.OnSuccess -> ShowResultUIEvents.OnSuccess(
        this.list.map { it.toUIModel() }
    )
}

fun EpisodesRequestStatus.toUIEvent(): EpisodeResultUIEvents = when (this) {
    is EpisodesRequestStatus.OnError -> EpisodeResultUIEvents.OnError(this.errorMessage)
    is EpisodesRequestStatus.OnLoading -> EpisodeResultUIEvents.OnLoading
    is EpisodesRequestStatus.OnSuccess -> EpisodeResultUIEvents.OnSuccess(
        this.list.map { it.toUIModel() }
    )
}
