package com.aldajo92.tvmazeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.aldajo92.tvmazeapp.ui.compose_utils.theme.TvMazeTheme
import com.aldajo92.tvmazeapp.ui.screens.ComposeApp
import com.aldajo92.tvmazeapp.ui.ui_components.ConnectionState
import com.aldajo92.tvmazeapp.ui.ui_components.NoInternetConnectionBar
import com.aldajo92.tvmazeapp.ui.ui_components.connectivityState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class, ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvMazeTheme {
                Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
                    val connection by connectivityState()
                    if (connection === ConnectionState.Unavailable) NoInternetConnectionBar()
                    ComposeApp()
                }
            }
        }
    }

}