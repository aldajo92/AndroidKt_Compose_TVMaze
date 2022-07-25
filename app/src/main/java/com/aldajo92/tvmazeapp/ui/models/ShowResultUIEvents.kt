package com.aldajo92.tvmazeapp.ui.models

sealed class ShowResultUIEvents {

    class OnError(val errorMessage: String) : ShowResultUIEvents()

    object OnLoading : ShowResultUIEvents()

    class OnSuccess(val list: List<ShowUIModel> = emptyList()) : ShowResultUIEvents()

}
