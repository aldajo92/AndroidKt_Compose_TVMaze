package com.aldajo92.tvmazeapp.ui.screens.episode

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aldajo92.tvmazeapp.R
import com.aldajo92.tvmazeapp.presentation.EpisodeViewModel
import com.aldajo92.tvmazeapp.ui.ui_components.AppBarWithArrow
import com.aldajo92.tvmazeapp.ui.ui_components.SummarySection

@Composable
fun EpisodeScreen(
    episodeId: String = "EpisodeId",
    pressOnBack: () -> Unit = {}
) {
    val viewModel = hiltViewModel<EpisodeViewModel>()
    viewModel.getEpisodeDetails(episodeId)

    val selectedEpisodeState = viewModel.selectedEpisodeLiveData.observeAsState()

    val barTitle = "Season ${
        selectedEpisodeState.value?.season.toString()
    } : Episode ${
        selectedEpisodeState.value?.number
    }"

    EpisodeSectionUI(
        barTitle,
        selectedEpisodeState.value?.name.orEmpty(),
        selectedEpisodeState.value?.imageHighURL,
        selectedEpisodeState.value?.summary.orEmpty(),
        pressOnBack
    )
}

@Preview
@Composable
fun EpisodeSectionUI(
    appBarTitle: String = "Season x",
    sectionTitleText: String = "Episode title",
    imageUrl: String? = "",
    textSummaryContent: String = "Loren posium",
    pressOnBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),
    ) {
        AppBarWithArrow(
            modifier = Modifier.fillMaxWidth(),
            appBarTitle, pressOnBack
        )
        if (!imageUrl.isNullOrBlank()) AsyncImage(
            model = imageUrl,
            modifier = Modifier.fillMaxWidth(),
            contentDescription = null
        ) else Image(
            painter = painterResource(R.drawable.episode_place_holder_high),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    text = "Title:"
                )
            }
            item {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    text = sectionTitleText
                )
            }
            if (textSummaryContent.isNotEmpty()) {
                item {
                    SummarySection(
                        modifier = Modifier.padding(top = 10.dp),
                        textSummaryContent = textSummaryContent
                    )
                }
            }
        }
    }
}
