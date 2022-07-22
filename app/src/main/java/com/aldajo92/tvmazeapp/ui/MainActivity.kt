package com.aldajo92.tvmazeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aldajo92.tvmazeapp.ui.compose_utils.theme.MyApplicationTheme
import com.aldajo92.tvmazeapp.ui.screens.main.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
//                ComposeApp()
                MainScreen()
            }
//            val navController = rememberNavController()
//            BottomNavGraph(navController)
        }
    }

    object Route {
        const val SHOW_LIST = "show_list"
        const val DETAIL = "detail"
    }

}
