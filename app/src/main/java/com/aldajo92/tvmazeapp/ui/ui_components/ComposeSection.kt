package com.aldajo92.tvmazeapp.ui.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SummarySection(
    modifier: Modifier = Modifier,
    textSummary: String = "Summary:",
    textSummaryContent: String = "Loren posium"
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = textSummary,
            color = MaterialTheme.colors.onBackground
        )
        HtmlText(
            text = textSummaryContent.ifEmpty { "[No summary available]" },
            color = MaterialTheme.colors.onBackground
        )
    }
}
