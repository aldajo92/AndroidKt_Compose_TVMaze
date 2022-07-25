package com.aldajo92.tvmazeapp.presentation.events

import com.aldajo92.tvmazeapp.network.home.ShowDTO

sealed class ShowsRequestStatus {

    class OnError(val errorMessage: String) : ShowsRequestStatus()

    object OnLoading : ShowsRequestStatus()

    class OnSuccess(val list: List<ShowDTO> = emptyList()) : ShowsRequestStatus()

}
