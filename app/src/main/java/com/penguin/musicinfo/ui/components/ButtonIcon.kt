package com.penguin.musicinfo.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.penguin.musicinfo.R


@Composable
fun ButtonIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.White,
    unselectedIcon: Int,
    selectedIcon: Int = unselectedIcon,
    onClick: () -> Unit = {},
) {
    var selected by remember { mutableStateOf(false) }

    IconButton(modifier = modifier, onClick = {
        selected = !selected
        onClick()
    }) {
        Icon(
            painter = if (selected) {
                painterResource(id = selectedIcon)
            } else {
                painterResource(id = unselectedIcon)
            },
            contentDescription = null,
            tint = tint
        )
    }
}


@Composable
@Preview
fun PreviewButtonIcon() {
    ButtonIcon(
        unselectedIcon = R.drawable.ic_unfavorite_24,
        selectedIcon = R.drawable.ic_favorite_24
    )
}