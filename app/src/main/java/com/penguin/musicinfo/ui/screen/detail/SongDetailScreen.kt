package com.penguin.musicinfo.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.penguin.musicinfo.ui.components.AppTopBar
import com.penguin.musicinfo.ui.components.SongResultItem
import com.penguin.musicinfo.ui.components.TextTitle

@Composable
fun SongDetailScreen(
    navController: NavHostController,
    songDetailViewModel: SongDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = songDetailViewModel.collectionId) {
        songDetailViewModel.getAlbumDetails()
    }

    DetailsScreen(navController, songDetailViewModel)
}

@Composable
fun DetailsScreen(
    navController: NavHostController,
    songDetailViewModel: SongDetailViewModel = hiltViewModel()
) {

    val tracks = songDetailViewModel.songsInAlbumState.value
    val albumInfo = songDetailViewModel.artistInfoState.value

    val listScrollState = rememberLazyListState()
    val contentHeight = 200.dp

    val alpha = remember { derivedStateOf {
        1f - listScrollState.firstVisibleItemScrollOffset / contentHeight.value } }

    LazyColumn(
        state = listScrollState,
        modifier = Modifier.padding(
            top = 50.dp,
            start = 20.dp,
            end = 10.dp
        ),
        contentPadding = PaddingValues(
            top = 80.dp,
            bottom = 100.dp
        )
    ) {
        item {
            Column(
                modifier = Modifier
                    .height(contentHeight)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom
            ) {
                AsyncImage(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .height(150.dp)
                        .width(150.dp)
                        .alpha(alpha.value)
                        .scale(alpha.value),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(albumInfo.imageUrl)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
                TextTitle(modifier = Modifier.padding(top = 10.dp), text = albumInfo.albumName)

                if (albumInfo.trackCount > 0) {
                    TextTitle(
                        text = "Track Count: ${albumInfo.trackCount}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }

        items(tracks) { track ->
            SongResultItem(track) {

            }
        }
    }

    Box {
        AppTopBar(titleText = albumInfo.artistName) { navController.popBackStack() }
    }
}

@Composable
@Preview(showBackground = true)
fun SongDetailScreenPreview() {
    SongDetailScreen(NavHostController(LocalContext.current), songDetailViewModel = hiltViewModel())
}