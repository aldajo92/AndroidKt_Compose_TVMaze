package com.aldajo92.tvmazeapp.ui.screens.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldajo92.tvmazeapp.R
import com.aldajo92.tvmazeapp.presentation.TVShowsViewModel
import com.aldajo92.tvmazeapp.ui.compose_utils.rememberForeverLazyListState
import com.aldajo92.tvmazeapp.ui.models.ShowResultUIEvents
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel
import com.aldajo92.tvmazeapp.ui.ui_components.ShowImageShimmer
import com.aldajo92.tvmazeapp.ui.ui_components.createShimmerBrush
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun SectionTVShowList(
    onItemClicked: (String) -> Unit
) {
    val viewModel = hiltViewModel<TVShowsViewModel>()
    val listResultState by viewModel.showEventsLiveData.observeAsState()
    val listState = rememberForeverLazyListState("Home")

    val currentShowListStatus = viewModel.currentShowList
    val showLoader = listResultState is ShowResultUIEvents.OnLoading

    RenderShowListResult(
        currentShowListStatus,
        listState,
        showLoader,
        onItemClicked
    ) {
        viewModel.loadNextShows()
    }
}

@Composable
fun RenderShowListResult(
    showList: List<ShowUIModel> = listOf(),
    state: LazyListState = rememberLazyListState(),
    showLoader: Boolean = true,
    onItemClicked: (String) -> Unit = {},
    endListReached: () -> Unit = {},
) {
    if (showList.isEmpty() && showLoader) Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(6) { ShimmerShowItem(Modifier.padding(start = 5.dp)) }
    }
    else LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        state = state,
        contentPadding = PaddingValues(vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(showList.size) { i ->
            RenderShowItem(item = showList[i], onItemClicked = onItemClicked)
            if (i >= showList.size - 1 && !showLoader) {
                endListReached()
            }
        }
        if (showLoader) {
            item { ShimmerShowItem(Modifier.padding(start = 5.dp)) }
        }
    }
}

@Composable
fun ShimmerShowItem(modifier: Modifier = Modifier, brush: Brush = createShimmerBrush()) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(Modifier.background(MaterialTheme.colors.background)) {
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 10.dp)
                    .size(height = 100.dp, width = 68.dp)
                    .background(brush)
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 5.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth(0.5f)
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth(0.9f)
                    .background(brush)
            )
        }
    }
}

@Composable
fun RenderShowItem(item: ShowUIModel, onItemClicked: (String) -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClicked(item.id) },
            horizontalArrangement = Arrangement.End
        ) {
            Column(
                Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = item.name, fontSize = 18.sp)
                HorizontalTextAnimation(textTitle = item.scheduleText)
            }
            Box(Modifier.background(MaterialTheme.colors.background)) {
                if (item.imageMediumURL.isNotBlank()) ShowImageShimmer(
                    modifier = Modifier
                        .size(100.dp),
                    imageUrl = item.imageMediumURL
                ) else Image(
                    painter = painterResource(R.drawable.place_holder_original),
                    modifier = Modifier
                        .size(100.dp),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun HorizontalTextAnimation(textTitle: String) {
    val durationMillis = 3000 + (2000 * Random.nextFloat()).toInt()
    val offset = Random.nextFloat() / 2
    val positionAnimation = remember { Animatable(0f + offset) }
    LaunchedEffect(positionAnimation) {
        launch {
            positionAnimation.animateTo(
                1f + offset,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = durationMillis, // remove the delay from infinite repeatable
                        easing = LinearEasing
                    )
                )
            )
        }
    }

    var size by remember { mutableStateOf(IntSize.Zero) }
    val currentPosition =
        ((size.width - 300) * (positionAnimation.value) % (size.width - 300)).let {
            if (it.isNaN()) 0f else it
        }
    Text(
        modifier = Modifier
            .offset(x = (-200).dp + currentPosition.dp)
            .fillMaxWidth()
            .onSizeChanged {
                size = it
            },
        text = textTitle,
        color = MaterialTheme.colors.onBackground
    )
}
