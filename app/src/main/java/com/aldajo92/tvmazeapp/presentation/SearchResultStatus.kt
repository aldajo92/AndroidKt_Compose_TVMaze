package com.aldajo92.tvmazeapp.presentation

import com.aldajo92.tvmazeapp.network.home.SearchResultDTO

sealed class SearchResultStatus {

    class OnError(val errorMessage: String) : SearchResultStatus()

    object OnLoading : SearchResultStatus()

    class OnSuccess(val list: List<SearchResultDTO> = emptyList()) : SearchResultStatus()

}
