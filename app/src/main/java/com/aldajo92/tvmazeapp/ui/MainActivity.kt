package com.aldajo92.tvmazeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aldajo92.tvmazeapp.ui.compose_utils.theme.MyApplicationTheme
import com.aldajo92.tvmazeapp.ui.screens.ComposeApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                ComposeApp()
            }
        }
    }

}
