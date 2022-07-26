package com.aldajo92.tvmazeapp.ui.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldajo92.tvmazeapp.presentation.SearchViewModel
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel
import com.aldajo92.tvmazeapp.ui.ui_components.SearchAppBar
import timber.log.Timber

@Composable
fun SectionSearch(
    onShowClicked: (String) -> Unit = {}
) {
    val viewModel = hiltViewModel<SearchViewModel>()

    val searchTextState by viewModel.searchTextState.observeAsState()
    val currentShowListState by viewModel.showListLiveData.collectAsState(listOf())
    val showLoader by viewModel.loadingLiveData.observeAsState(true)
    val selectedShowId by viewModel.updatedShowId.observeAsState("")
    Timber.d(selectedShowId)

    SectionSearchUI(
        searchTextState.orEmpty(),
        viewModel::updateSearchTextState,
        onCloseClicked = {
            viewModel.updateSearchTextState("")
        },
        onSearchClicked = viewModel::performSearch,
        showList = currentShowListState,
        showLoader = showLoader,
        onStartClicked = viewModel::markAsFavorite
    ) {
        viewModel.saveSelectedShow(it)
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
    onStartClicked: (String, Boolean) -> Unit = { _, _ -> },
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
            onStartClicked = onStartClicked,
            onShowClicked = onShowClicked
        )
    }
}
