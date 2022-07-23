package com.aldajo92.tvmazeapp.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AsyncImageShimmer(
    modifier: Modifier = Modifier,
    imageUrl: String,
    brush: Brush = createShimmerBrush()
) {
    val isLoading = remember { mutableStateOf(true) }
    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = imageUrl,
            contentDescription = null,
            onLoading = {
                isLoading.value = true
            },
            onSuccess = {
                isLoading.value = false
            }
        )
        if (isLoading.value) Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 5.dp)
                .background(brush)
        )
    }
}

@Composable
fun ShowImageShimmer(
    modifier: Modifier = Modifier,
    imageUrl: String,
    brush: Brush = createShimmerBrush()
) {
    val isLoading = remember { mutableStateOf(true) }
    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .padding(5.dp)
                .size(100.dp),
            model = imageUrl,
            contentDescription = null
        )
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = imageUrl,
            contentDescription = null,
            onLoading = {
                isLoading.value = true
            },
            onSuccess = {
                isLoading.value = false
            }
        )
        if (isLoading.value) Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(height = 100.dp, width = 68.dp)
                .padding(horizontal = 5.dp)
                .background(brush)
        )
    }
}