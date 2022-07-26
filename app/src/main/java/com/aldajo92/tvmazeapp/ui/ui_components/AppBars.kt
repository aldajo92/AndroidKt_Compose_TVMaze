@file:OptIn(ExperimentalComposeUiApi::class)

package com.aldajo92.tvmazeapp.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aldajo92.tvmazeapp.R
import com.aldajo92.tvmazeapp.ui.compose_utils.theme.WhiteColor

@Preview
@Composable
fun SearchAppBar(
    text: String = "",
    onTextChange: (String) -> Unit = { _ -> },
    onCloseClicked: () -> Unit = {},
    onSearchClicked: (String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.surface
    ) {
        TextField(modifier = Modifier.fillMaxWidth(), value = text, onValueChange = {
            onTextChange(it)
        }, placeholder = {
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium), text = "Search here...",
                color = MaterialTheme.colors.onSurface
            )
        },
            textStyle = TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize),
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(ContentAlpha.medium), onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }, trailingIcon = {
                IconButton(modifier = Modifier.alpha(ContentAlpha.medium), onClick = {
                    if (text.isNotEmpty()) {
                        onTextChange("")
                        keyboardController?.hide()
                    } else {
                        onCloseClicked()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                    keyboardController?.hide()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}

@Preview
@Composable
fun AppBarWithArrow(
    title: String? = "CustomTitle",
    showStarIcon: Boolean = true,
    starMarked: Boolean = false,
    onStarClicked: () -> Unit = {},
    pressOnBack: () -> Unit = {},
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .background(color = MaterialTheme.colors.primary),
        actions = {
            if (showStarIcon) IconButton(
                modifier = Modifier.alpha(ContentAlpha.medium),
                onClick = onStarClicked
            ) {
                Icon(
                    painter = painterResource(
                        if (starMarked) R.drawable.ic_star_marked else R.drawable.ic_star
                    ),
                    contentDescription = "Icon Favorite",
                    tint = WhiteColor,
                )
            }
        },
        navigationIcon = {
            IconButton(modifier = Modifier
                .alpha(ContentAlpha.medium),
                onClick = {
                    pressOnBack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Search Icon",
                    tint = WhiteColor
                )
            }
        },
        title = {
            Text(
                modifier = Modifier
                    .padding(4.dp),
                text = title ?: "",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    )
}
