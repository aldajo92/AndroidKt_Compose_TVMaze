package com.aldajo92.tvmazeapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.presentation.TVShowsViewModel
import com.aldajo92.tvmazeapp.ui.compose_utils.rememberForeverLazyListState

@Composable
fun SectionTVShowList(
    onItemClicked: (String) -> Unit
) {
    val viewModel = hiltViewModel<TVShowsViewModel>()
    val uiState = viewModel.listShowData.observeAsState(listOf())
    val listState = rememberForeverLazyListState("Overview")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        state = listState
    ) {
        uiState.value.map {
            item {
                RenderShowItem(item = it, onItemClicked = onItemClicked)
            }
        }
    }
}

@Composable
fun RenderShowItem(item: ShowDTO, onItemClicked: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onItemClicked(item.id) }) {
        item.image?.get("medium")?.let {
            AsyncImage(
                modifier = Modifier
                    .size(60.dp),
                model = it,
                contentDescription = null
            )
        }
        Text(
            text = item.name,
            color = MaterialTheme.colors.onBackground
        )
    }
}
