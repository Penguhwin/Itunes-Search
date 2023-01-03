package com.penguin.musicinfo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.penguin.musicinfo.R
import com.penguin.musicinfo.network.data.model.SongDataModel


@Composable
fun SongResultItem(
    dataModel: SongDataModel,
    onClick: () -> Unit? = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.Top)
                .height(50.dp)
                .width(50.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            model = ImageRequest.Builder(LocalContext.current)
                .data(dataModel.imageUrl)
                .build()
        )
        Column(
            Modifier
                .padding(horizontal = 8.dp)
                .weight(2f)
        ) {
            TextTitle(
                text = dataModel.trackName,
                fontSize = 15.sp
            )

            Row {
                if (dataModel.isExplicit) {
                    ButtonIcon(
                        modifier = Modifier
                            .width(13.dp)
                            .height(13.dp)
                            .align(Alignment.CenterVertically)
                            .padding(end = 2.dp),
                        unselectedIcon = R.drawable.explicit
                    )
                }
                TextTitle(
                    text = dataModel.artistName,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
            }
        }
        ButtonIcon(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp),
            selectedIcon = R.drawable.ic_favorite_24,
            unselectedIcon = R.drawable.ic_unfavorite_24,
        )
    }
}

@Composable
@Preview
fun PreviewSongResultItem() {
    val songDataModel = SongDataModel(
        trackName = "Started from the Bottom now we're here",
        artistName = "Drake",
        imageUrl = "",
        isExplicit = true
    )
    SongResultItem(songDataModel)
}