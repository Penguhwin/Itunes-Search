# ArtistInfo App
Music app that is build on Jetpack Compose UI and it queries the Itunes API for fetching songs/album
information

## Wanted to finish features
*Proper error handling in each layer, and recovery/refresh options when API fails
A Row for Artists on the search page -> currently Itunes API does not have Artist Artwork just yet
*Local database (maybe room) for storing favorites songs/artists
*Placeholder images/textbox that will indicate a loading state while a screen is loading
*Performance metrics capturing:
    app start-up, network timing, serialization, async image rendering time (from Coil)
    Jetpack Compose -> # of Recompositions, UI Render Time
    Cumulative Layout Shifts
*Integrate with Datadog and Firebase Performance to send out custom metrics + error logging 
*Automated UI Tests:
    - Smoke testing screens and navigation
    - Assertion tests on Composable functions and the layout hierarchy

## Stack
Hilt Dependency Injection
*MVVM Architecture
*Jetpack compose
*Compose Navigation
*Tech Stack
*Retrofit
*Coroutines


Itunes Search API: https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/index.html


# MACROBENCHMARK UI
Device: Samsung S23U

Start-up Tests 
![](https://github.com/Penguhwin/Itunes-Search/blob/main/Media_230111_121145.gif)
StartupBenchmark_hotStartup
timeToInitialDisplayMs   min 44.7,   median 58.8,   max 73.9

StartupBenchmark_coldStartup
timeToInitialDisplayMs   min 2,356.5,   median 2,372.9,   max 2,391.0



Search Action Test
![](https://github.com/Penguhwin/Itunes-Search/blob/main/Media_230111_122126.gif)
SearchScreenBenchmark_searchScreenDrakeSearch
frameDurationCpuMs   P50  10.7,   P90  23.7,   P95  35.0,   P99  67.0
frameOverrunMs   P50  -4.7,   P90   7.9,   P95  19.1,   P99  51.5

SearchScreenBenchmark_searchScreenScrolling
frameDurationCpuMs   P50  10.2,   P90  19.7,   P95  32.0,   P99  33.2
frameOverrunMs   P50  -2.6,   P90  16.3,   P95  18.7,   P99  40.7

