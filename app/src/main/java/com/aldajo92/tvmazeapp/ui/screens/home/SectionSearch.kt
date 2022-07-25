package com.aldajo92.tvmazeapp.ui.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldajo92.tvmazeapp.presentation.SearchViewModel
import com.aldajo92.tvmazeapp.ui.models.ShowResultUIEvents
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel
import com.aldajo92.tvmazeapp.ui.ui_components.SearchAppBar
import timber.log.Timber

@Composable
fun SectionSearch(
    onShowClicked: (String) -> Unit = {}
) {
    val viewModel = hiltViewModel<SearchViewModel>()

    val searchTextState by viewModel.searchTextState.observeAsState()
    val searchResultState by viewModel.searchResultsLiveData.observeAsState()

    val searchResultList: List<ShowUIModel>
    val showLoader: Boolean

    when (searchResultState) {
        is ShowResultUIEvents.OnSuccess -> {
            searchResultList = (searchResultState as ShowResultUIEvents.OnSuccess).list
            showLoader = false
        }
        is ShowResultUIEvents.OnLoading -> {
            searchResultList = listOf()
            showLoader = true
        }
        else -> {
            searchResultList = listOf()
            showLoader = false
        }
    }

    Timber.d(searchTextState)

    SectionSearchUI(
        searchTextState.orEmpty(),
        viewModel::updateSearchTextState,
        onCloseClicked = {
            viewModel.updateSearchTextState("")
        },
        onSearchClicked = viewModel::performSearch,
        showList = searchResultList,
        showLoader = showLoader
    ) {
        Timber.i(it)
        viewModel.setSelectedShow(it)
        onShowClicked(it)
    }

}

@Preview
@Composable
fun SectionSearchUI(
    searchTextState: String = "",
    onTextChange: (String) -> Unit = { _ -> },
    onCloseClicked: () -> Unit = {},
    onSearchClicked: (String) -> Unit = { _ -> },
    showList: List<ShowUIModel> = listOf(),
    showLoader: Boolean = true,
    onShowClicked: (String) -> Unit = {}
) {
    Scaffold(topBar = {
        SearchAppBar(
            text = searchTextState,
            onTextChange = onTextChange,
            onCloseClicked = onCloseClicked,
            onSearchClicked = onSearchClicked,
        )
    }) {
        RenderShowListResult(
            showList = showList,
            showLoader = showLoader,
            onItemClicked = onShowClicked
        )
    }
}
