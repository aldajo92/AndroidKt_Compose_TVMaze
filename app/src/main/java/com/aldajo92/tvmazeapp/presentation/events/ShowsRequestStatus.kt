package com.aldajo92.tvmazeapp.presentation.events

import com.aldajo92.tvmazeapp.domain.Show

sealed class ShowsRequestStatus {

    class OnError(val errorMessage: String) : ShowsRequestStatus()

    object OnLoading : ShowsRequestStatus()

    class OnSuccess(val list: List<Show> = emptyList()) : ShowsRequestStatus()

}
