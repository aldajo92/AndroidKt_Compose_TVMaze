package com.aldajo92.tvmazeapp.ui.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldajo92.tvmazeapp.presentation.SearchViewModel
import com.aldajo92.tvmazeapp.ui.compose_utils.rememberForeverLazyListState
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel
import com.aldajo92.tvmazeapp.ui.ui_components.DefaultAppBar
import com.aldajo92.tvmazeapp.ui.ui_components.SearchAppBar
import com.aldajo92.tvmazeapp.ui.ui_components.SearchWidgetState
import timber.log.Timber

@Composable
fun SectionSearch(
    onShowClicked: (String) -> Unit = {}
) {

    val viewModel = hiltViewModel<SearchViewModel>()

    val searchWidgetState by viewModel.searchWidgetState.observeAsState()
    val searchTextState by viewModel.searchTextState.observeAsState()
    val searchResult by viewModel.searchResultsLiveData.observeAsState()

    SectionSearchUI(
        searchWidgetState ?: SearchWidgetState.CLOSED,
        searchTextState.orEmpty(),
        viewModel::updateSearchTextState,
        onCloseClicked = {
            viewModel.updateSearchTextState("")
            viewModel.updateSearchWidgetState(SearchWidgetState.CLOSED)
        },
        onSearchClicked = viewModel::performSearch,
        onSearchTriggered = {
            viewModel.updateSearchWidgetState(SearchWidgetState.OPENED)
        },
        searchResult ?: listOf()
    ) {
        Timber.i(it)
        viewModel.setSelectedShow(it)
        onShowClicked(it)
    }

}

@Preview
@Composable
fun SectionSearchUI(
    searchWidgetState: SearchWidgetState = SearchWidgetState.CLOSED,
    searchTextState: String = "",
    onTextChange: (String) -> Unit = { _ -> },
    onCloseClicked: () -> Unit = {},
    onSearchClicked: (String) -> Unit = { _ -> },
    onSearchTriggered: () -> Unit = {},
    list: List<ShowUIModel> = listOf(),
    onShowClicked: (String) -> Unit = {}
) {
    val listState = rememberForeverLazyListState("SearchResult")
    Scaffold(topBar = {
        MazeSearchAppBar(
            searchWidgetState,
            searchTextState,
            onTextChange,
            onCloseClicked,
            onSearchClicked,
            onSearchTriggered,
        )
    }) {
        RenderShowListResult(list, listState, onShowClicked)
    }
}

@Preview
@Composable
fun MazeSearchAppBar(
    searchWidgetState: SearchWidgetState = SearchWidgetState.CLOSED,
    searchTextState: String = "",
    onTextChange: (String) -> Unit = { _ -> },
    onCloseClicked: () -> Unit = {},
    onSearchClicked: (String) -> Unit = { _ -> },
    onSearchTriggered: () -> Unit = {}
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> DefaultAppBar(
            onSearchClicked = onSearchTriggered
        )
        SearchWidgetState.OPENED -> SearchAppBar(
            text = searchTextState,
            onTextChange = onTextChange,
            onCloseClicked = onCloseClicked,
            onSearchClicked = onSearchClicked,
        )
    }
}