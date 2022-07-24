package com.aldajo92.tvmazeapp.ui.models

sealed class SearchResultUIEvents {

    class OnError(val errorMessage: String) : SearchResultUIEvents()

    object OnLoading : SearchResultUIEvents()

    class OnSuccess(val list: List<ShowUIModel> = emptyList()) : SearchResultUIEvents()

}