package com.aldajo92.tvmazeapp.presentation

import com.aldajo92.tvmazeapp.network.home.ShowDTO

sealed class ShowRequestStatus {

    class OnError(val errorMessage: String) : ShowRequestStatus()

    object OnLoading : ShowRequestStatus()

    class OnSuccess(val list: List<ShowDTO> = emptyList()) : ShowRequestStatus()

}
