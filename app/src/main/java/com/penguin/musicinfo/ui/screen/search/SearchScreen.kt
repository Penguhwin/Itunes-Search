package com.penguin.musicinfo.ui.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.penguin.musicinfo.R
import com.penguin.musicinfo.ui.components.AppBottomNav
import com.penguin.musicinfo.ui.components.ButtonIcon
import com.penguin.musicinfo.ui.components.SongResultItem
import com.penguin.musicinfo.ui.nav.Screen
import com.penguin.musicinfo.ui.theme.ContentPaddingMedium
import com.penguin.musicinfo.ui.theme.ContentPaddingSmall
import com.penguin.musicinfo.ui.theme.ContentPaddingTiny


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val songSearchResult = viewModel.songSearchState.value.collectAsLazyPagingItems()

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val listState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        scaffoldState = scaffoldState,
        topBar = {
            SearchAppBar(
                modifier = Modifier.padding(horizontal = 10.dp),
                onSearch = { viewModel.onSearchAction() })
        },
        bottomBar = {
            AppBottomNav(Modifier.navigationBarsPadding(), navController, Screen.Search)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(ContentPaddingMedium)
                .testTag("searchScreenLazyColumn"),
            horizontalAlignment = Alignment.Start,
            contentPadding = paddingValues,
            state = listState
        ) {
            // Using Android Paging3 to handle infinite scroll for results
            when (songSearchResult.loadState.refresh) {
                is LoadState.NotLoading -> {
                    viewModel.isSearchingState.value = false

                    items(songSearchResult) { result ->
                        val focus = LocalFocusManager.current
                        if (result != null) {
                            SongResultItem(dataModel = result) {
                                // Navigate to the Details Screen
                                if (result.collectionId.isNotEmpty())
                                    navController.navigate(
                                        "${Screen.SongDetail.route}/${result.collectionId}"
                                    )
                            }
                        }
                        focus.clearFocus()
                    }

                    if (songSearchResult.itemCount == 0) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.not_found),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }

                // When the list is loading for data, a progress bar will be displayed
                is LoadState.Loading -> {
                    if (viewModel.isSearchingState.value) {
                        item {
                            CircularProgressBar()
                        }
                    }
                }

                else -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 60.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.not_found),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CircularProgressBar() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun SearchAppBar(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onSearch: () -> Unit,
    titleModifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current
) {
    var query by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        var focused by remember { mutableStateOf(false) }

        val search :() -> Unit = {
            onSearch()
            keyboardController?.hide()
            focusManager.clearFocus()
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(ContentPaddingSmall)
        ) {
            Text(
                text = stringResource(R.string.search_title),
                style = MaterialTheme.typography.h3,
                modifier = titleModifier.padding(ContentPaddingMedium),
            )

            SearchBar(
                modifier = Modifier
                    .padding(horizontal = ContentPaddingMedium)
                    .height(50.dp)
                    .onFocusChanged {
                        focused = it.isFocused
                    }
                    .semantics { testTagsAsResourceId = true },
                text = query,
                onValueChange = { value ->
                    query = value
                    val searchInput = if (value.trim().isNotEmpty()) value else ""
                    viewModel.searchTerm.value = searchInput
                },
                onSearch = search)
        }
    }
}

@Composable
fun SearchBar(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (value: String) -> Unit = {},
    onSearch: () -> Unit = {},
) {
    var focused by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(horizontal = ContentPaddingTiny)
            .onFocusChanged {
                focused = it.isFocused
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        ButtonIcon(
            modifier = Modifier
                .size(24.dp)
                .testTag("searchBarButton"),
            tint = Color.Black,
            unselectedIcon = R.drawable.ic_baseline_search_24,
            onClick = onSearch
        )

        TextField(
            value = text,
            onValueChange = { newVal -> onValueChange(newVal) },
            modifier = Modifier
                .weight(1f)
                .testTag("searchBarTextField"),
            textStyle = TextStyle(
                color = Color.DarkGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.DarkGray,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = "Songs, artists, albums...", style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 14.sp
                    )
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.None
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearch() })
        )
    }
}

