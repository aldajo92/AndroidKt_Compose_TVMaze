package com.aldajo92.tvmazeapp.presentation.events

import com.aldajo92.tvmazeapp.domain.Episode

sealed class EpisodesRequestStatus {

    class OnError(val errorMessage: String) : EpisodesRequestStatus()

    object OnLoading : EpisodesRequestStatus()

    class OnSuccess(val list: List<Episode> = emptyList()) : EpisodesRequestStatus()

}
