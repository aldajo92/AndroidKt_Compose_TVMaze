package com.aldajo92.tvmazeapp.ui.models

sealed class EpisodeResultUIEvents {

    class OnError(val errorMessage: String) : EpisodeResultUIEvents()

    object OnLoading : EpisodeResultUIEvents()

    class OnSuccess(val list: List<EpisodeUIModel> = emptyList()) : EpisodeResultUIEvents()

}
