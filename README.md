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


Itunes Search API:
src<https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/index.html>
