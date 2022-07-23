package com.aldajo92.tvmazeapp.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aldajo92.tvmazeapp.R
import com.aldajo92.tvmazeapp.presentation.ShowDetailViewModel
import com.aldajo92.tvmazeapp.ui.models.EpisodeUIModel
import com.aldajo92.tvmazeapp.ui.ui_components.AppBarWithArrow
import com.aldajo92.tvmazeapp.ui.ui_components.HtmlText
import com.aldajo92.tvmazeapp.ui.ui_components.RatingBar

@Composable
fun DetailsScreen(
    showId: String = "showId",
    pressOnBack: () -> Unit = {},
    episodeClicked: (episodeId: String) -> Unit = { _ -> }
) {

    val viewModel = hiltViewModel<ShowDetailViewModel>()
    viewModel.getShowDetail(showId)

    val selectedShowState = viewModel.selectedShowLiveData.observeAsState()
    val episodesState = viewModel.episodesLiveData.observeAsState()

    DetailScreenUI(
        selectedShowState.value?.name.orEmpty(),
        selectedShowState.value?.imageHighURL,
        selectedShowState.value?.raiting ?: 0f,
        selectedShowState.value?.scheduleText.orEmpty(),
        selectedShowState.value?.genres?.reduce { acc, s -> "$acc, $s" } ?: "",
        episodesState.value ?: emptyList(),
        selectedShowState.value?.summary.orEmpty(),
        pressOnBack,
        episodeClicked
    )
}

@Preview
@Composable
fun DetailScreenUI(
    sectionTitleText: String = "",
    imageUrl: String? = "",
    rating: Float = 0f,
    textScheduleValue: String = "Lun, Tue, Wed : 21:00",
    textGeneresValue: String = "Genere1, Genere2, Genere3",
    episodesList: List<EpisodeUIModel> = listOf(),
    textSummaryContent: String = "Lorem posium",
    pressOnBack: () -> Unit = {},
    episodeClicked: (episodeId: String) -> Unit = { _ -> }
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),
    ) {
        AppBarWithArrow(
            modifier = Modifier.fillMaxWidth(),
            sectionTitleText, pressOnBack
        )
        Row(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .background(MaterialTheme.colors.background)
        ) {
            if (!imageUrl.isNullOrBlank()) AsyncImage(
                modifier = Modifier
                    .size(200.dp),
                model = imageUrl,
                contentDescription = null
            ) else Image(
                painter = painterResource(R.drawable.place_holder_original),
                modifier = Modifier
                    .size(200.dp),
                contentDescription = null
            )
            ContentHeader(
                rateValue = rating / 2f,
                textGeneresValue = textGeneresValue,
                textScheduleValue = textScheduleValue
            )
        }

        val seasonMap = episodesList.groupBy { it.season }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item { SummarySection(textSummaryContent = textSummaryContent) }
            seasonMap.map { entry ->
                item {
                    SeasonSection(
                        "Season ${entry.key}",
                        entry.value,
                        episodeClicked
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ContentHeader(
    textRating: String = "Rating:",
    rateValue: Float? = 4.0f,
    textGeneres: String = "Generes:",
    textGeneresValue: String = "Genere1, Genere2, Genere3",
    textSchedule: String = "Schedule:",
    textScheduleValue: String = "Lun, Tue, Wed : 21:00",
) {
    Column(
        modifier = Modifier.padding(end = 20.dp)
    ) {
        Text(
            text = textRating,
            color = MaterialTheme.colors.onBackground
        )
        if (rateValue != null) RatingBar(
            modifier = Modifier.height(20.dp),
            rating = rateValue
        )
        else Text(text = "[No rate available]")

        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = textGeneres,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = textGeneresValue,
            color = MaterialTheme.colors.onBackground
        )

        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = textSchedule,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = textScheduleValue,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
fun SummarySection(
    textSummary: String = "Summary:",
    textSummaryContent: String = "Loren posium"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = textSummary,
            color = MaterialTheme.colors.onBackground
        )
        HtmlText(
            text = textSummaryContent,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
fun SeasonSection(
    seasonTitle: String = "Episodes:",
    episodesList: List<EpisodeUIModel> = listOf(),
    episodeClicked: (episodeId: String) -> Unit = { _ -> }
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = seasonTitle,
            color = MaterialTheme.colors.onBackground
        )
        LazyRow(Modifier.fillMaxWidth()) {
            episodesList.map { episode ->
                item {
                    RenderEpisodeItem(episode.name, episode.imageMediumURL) {
                        episodeClicked(episode.id)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RenderEpisodeItem(
    name: String = "Episode Title",
    imageUrl: String? = "",
    episodeClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .clickable { episodeClicked() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!imageUrl.isNullOrBlank()) AsyncImage(
            model = imageUrl,
            contentDescription = null
        ) else Image(
            modifier = Modifier.padding(5.dp),
            painter = painterResource(R.drawable.episode_place_holder_original),
            contentDescription = null
        )
        Text(
            text = name,
            fontSize = 10.sp,
            color = MaterialTheme.colors.onBackground
        )
    }
}
