package com.aldajo92.tvmazeapp.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldajo92.tvmazeapp.presentation.FavoritesViewModel
import com.aldajo92.tvmazeapp.ui.compose_utils.rememberForeverLazyListState
import com.aldajo92.tvmazeapp.ui.models.ShowResultUIEvents
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel

@Composable
fun SectionFavorite() {

    val viewModel = hiltViewModel<FavoritesViewModel>()

    val favoritesResultState by viewModel.favoritesResultsLiveData.observeAsState()
    val searchResultList: List<ShowUIModel> =
        if (favoritesResultState is ShowResultUIEvents.OnSuccess) {
            (favoritesResultState as ShowResultUIEvents.OnSuccess).list
        } else listOf()
    val listState = rememberForeverLazyListState("Favorites")

    RenderShowListResult(
        searchResultList,
        listState,
        false,
        onStartClicked = { showId, _ -> viewModel.deleteShowFromFavorites(showId) }
    ) {

    }

}
