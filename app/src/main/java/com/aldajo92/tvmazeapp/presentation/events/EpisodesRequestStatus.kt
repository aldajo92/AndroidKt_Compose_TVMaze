package com.aldajo92.tvmazeapp.presentation.events

import com.aldajo92.tvmazeapp.network.home.EpisodeDTO

sealed class EpisodesRequestStatus {

    class OnError(val errorMessage: String) : EpisodesRequestStatus()

    object OnLoading : EpisodesRequestStatus()

    class OnSuccess(val list: List<EpisodeDTO> = emptyList()) : EpisodesRequestStatus()

}
