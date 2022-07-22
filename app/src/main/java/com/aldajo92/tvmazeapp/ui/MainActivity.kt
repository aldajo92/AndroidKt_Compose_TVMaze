package com.aldajo92.tvmazeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.aldajo92.tvmazeapp.ui.compose_utils.theme.TvMazeTheme
import com.aldajo92.tvmazeapp.ui.screens.ComposeApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvMazeTheme {
                Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
                    ComposeApp()
                }
            }
        }
    }

}
