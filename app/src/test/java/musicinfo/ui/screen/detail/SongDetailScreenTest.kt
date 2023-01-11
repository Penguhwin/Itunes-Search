package musicinfo.ui.screen.detail

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.penguin.musicinfo.network.data.model.AlbumDataModel
import com.penguin.musicinfo.network.data.model.SongDataModel
import com.penguin.musicinfo.ui.screen.detail.DetailsScreen
import com.penguin.musicinfo.ui.theme.ArtistInfoTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class SongDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testEmptyDetailsScreen() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        composeTestRule.setContent {
            ArtistInfoTheme {
                DetailsScreen(
                    navController,
                    listOf(),
                    AlbumDataModel("", "", "", "", 0, "", "")
                )
            }
        }
        // 1 Child for the Track name TextField, and no items
        composeTestRule.onNodeWithTag("detailsScreenColumn").onChildren().assertCountEquals(1)

        // When track is < 0 songs, nothing should be displayed
        composeTestRule.onNodeWithTag("detailsScreenColumn")
            .onChild().assertIsNotDisplayed()
    }

    @Test
    fun oneSongInDetailsScreen() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        val albumName = "Take Care (Deluxe Version)"
        val trackCount = 1

        composeTestRule.setContent {
            ArtistInfoTheme {
                DetailsScreen(
                    navController,
                    listOf(
                        SongDataModel(
                            "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/74/fb/d3/74fbd365-bd52-23b4-604b-7f164407b0a9/00602527899107.rgb.jpg/100x100bb.jpg",
                            artistId = "271256",
                            isExplicit = true,
                            artistName = "Drake"
                        )
                    ),
                    AlbumDataModel(
                        "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/74/fb/d3/74fbd365-bd52-23b4-604b-7f164407b0a9/00602527899107.rgb.jpg/100x100bb.jpg",
                        "271256",
                        "Drake",
                        albumName,
                        trackCount,
                        "",
                        ""
                    )
                )
            }
        }
        // 1 Child for the Track name TextField, and no items
        composeTestRule.onNodeWithTag("detailsScreenColumn").onChildAt(0)
            .assertIsDisplayed()
            .assertTextEquals(albumName)

        composeTestRule.onNodeWithTag("detailsScreenColumn").onChildAt(1)
            .assertTextEquals("Track Count: $trackCount")
    }

    @Test
    fun navigateBack() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        composeTestRule.setContent {
            ArtistInfoTheme {
                DetailsScreen(
                    navController,
                    listOf(),
                    AlbumDataModel("", "", "", "", 0, "", "")
                )
            }
        }
        // 1 Child for the Track name TextField, and no items
        composeTestRule.onNodeWithTag("detailsScreenColumn")
            .onChildren()
            .assertCountEquals(1)

        // When track is < 0 songs, nothing should be displayed
        composeTestRule.onNodeWithTag("detailsScreenColumn")
            .onChild()
            .assertIsNotDisplayed()
    }


    companion object {
        // debug output of the empty SemanticsNode tree
        private const val EMPTY_COMPOSABLE_LAYOUT =
            "Printing with useUnmergedTree = 'false'\n" +
                    "Node #1 at (l=0.0, t=0.0, r=320.0, b=430.0)px\n" +
                    " |-Node #2 at (l=20.0, t=50.0, r=310.0, b=430.0)px\n" +
                    " | VerticalScrollAxisRange = 'ScrollAxisRange(value=0.0, maxValue=0.0, reverseScrolling=false)'\n" +
                    " | CollectionInfo = 'androidx.compose.ui.semantics.CollectionInfo@186ac69f'\n" +
                    " | Actions = [IndexForKey, ScrollBy, ScrollToIndex]\n" +
                    " |  |-Node #15 at (l=20.0, t=290.0, r=20.0, b=330.0)px\n" +
                    " |    Text = '[]'\n" +
                    " |    Actions = [GetTextLayoutResult]\n" +
                    " |-Node #4 at (l=0.0, t=0.0, r=320.0, b=56.0)px\n" +
                    "    |-Node #7 at (l=16.0, t=16.0, r=40.0, b=40.0)px\n" +
                    "    | Role = 'Button'\n" +
                    "    | Focused = 'false'\n" +
                    "    | ContentDescription = '[BackArrow]'\n" +
                    "    | Actions = [OnClick, RequestFocus]\n" +
                    "    | MergeDescendants = 'true'\n" +
                    "    |-Node #10 at (l=72.0, t=8.0, r=72.0, b=49.0)px\n" +
                    "      Text = '[]'\n" +
                    "      Actions = [GetTextLayoutResult]"
    }
}