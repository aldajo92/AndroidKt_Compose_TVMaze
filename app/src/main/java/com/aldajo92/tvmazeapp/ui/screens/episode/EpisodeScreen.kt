package com.aldajo92.tvmazeapp.ui.screens.episode

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aldajo92.tvmazeapp.R
import com.aldajo92.tvmazeapp.ui.ui_components.AppBarWithArrow

@Composable
fun EpisodeScreen(
    episodeId: String = "EpisodeId",
    pressOnBack: () -> Unit = {}
) {
    EpisodeSectionUI(
        "Section",
        "",
        pressOnBack
    )
}

@Preview
@Composable
fun EpisodeSectionUI(
    sectionTitleText: String = "",
    imageUrl: String? = "",
    pressOnBack: () -> Unit = {}
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
//            ContentHeader(
//                rateValue = rating / 2f,
//                textGeneresValue = textGeneresValue,
//                textScheduleValue = textScheduleValue
//            )
        }

//        val seasonMap = episodesList.groupBy { it.season }
//
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(20.dp)
//        ) {
//            item { SummarySection(textSummaryContent = textSummaryContent) }
//            seasonMap.map { entry ->
//                item {
//                    SeasonSection(
//                        "Season ${entry.key}",
//                        entry.value,
//                        episodeClicked
//                    )
//                }
//            }
//        }
    }
}