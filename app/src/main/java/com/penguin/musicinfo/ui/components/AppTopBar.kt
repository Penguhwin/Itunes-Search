package com.penguin.musicinfo.ui.components

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppTopBar(modifier: Modifier = Modifier, titleText: String = "", onBackPressed: () -> Unit) {
    TopAppBar(
        modifier = modifier.statusBarsPadding(),
        title = { Text(titleText) },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(Icons.Default.ArrowBack, "BackArrow")
            }
        }
    )
}

@Composable
@Preview
fun PreviewAppTopBar() {
    AppTopBar(Modifier, titleText = "Top Bar", onBackPressed = { /* handle back button press */ })
}
