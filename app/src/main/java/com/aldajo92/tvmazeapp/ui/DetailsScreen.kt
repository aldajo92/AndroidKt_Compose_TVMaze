package com.aldajo92.tvmazeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldajo92.tvmazeapp.presentation.ShowDetailViewModel

@Composable
fun DetailsScreen(showDetail: String = "") {

    val viewModel = hiltViewModel<ShowDetailViewModel>()
    viewModel.getSelectedShow(showDetail)

    val selectedShow = viewModel.selectedShowLiveData.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        selectedShow.value?.let {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = it.name,
                color = MaterialTheme.colors.onBackground
            )
        }
    }

}
