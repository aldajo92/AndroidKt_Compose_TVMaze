package com.aldajo92.tvmazeapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.presentation.MainViewModel

@Composable
fun ShowsScreen(
    onItemClicked: (String) -> Unit
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val uiState = viewModel.listShowData.observeAsState(listOf())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        uiState.value.map {
            item {
                RenderShowItem(item = it, onItemClicked = onItemClicked)
            }
        }
    }
}

// TODO: Use UI Models
@Composable
fun RenderShowItem(item: ShowDTO, onItemClicked: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onItemClicked(item.id.toString()) }) {
        item.image?.get("medium")?.let {
            AsyncImage(
                modifier = Modifier
                    .size(60.dp),
                model = it,
                contentDescription = null
            )
        }
        Text(text = item.name)
    }
}
