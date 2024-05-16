package com.example.weather.ui.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.TextField
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weather.data.DetailsResult
import com.example.weather.data.Episode
import com.example.weather.data.EpisodeResult
import com.example.weather.data.TVresult
import com.example.weather.data.showCategory

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    seriesId: String
) {
    var selectedSeason by rememberSaveable {
        mutableStateOf(1)
    }

    LaunchedEffect(Unit) {
        mainViewModel.getDetails(seriesId, "en-US", "0ee8e741a484dbcebd23c922f5a8a778")
        mainViewModel.getEpisodes(seriesId,  selectedSeason.toString(), "0ee8e741a484dbcebd23c922f5a8a778")
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        when (mainViewModel.detailsUiState) {
            is DetailsUiState.Init -> {}
            is DetailsUiState.Loading -> CircularProgressIndicator()
            is DetailsUiState.Success ->
                DetailsPhotoCard(
                    (mainViewModel.detailsUiState as DetailsUiState.Success).detailsResult,
                    seriesId,
                    onSeasonSelected = { selectedSeason = it }
                )
            is DetailsUiState.Error -> Text(
                text = "Error: " +
                        "${(mainViewModel.detailsUiState as DetailsUiState.Error).errorMsg}"
            )
        }

        when (mainViewModel.seasonsUiState) {
            is SeasonsUiState.Init -> {}
            is SeasonsUiState.Loading -> CircularProgressIndicator()
            is SeasonsUiState.Success ->
                EpisodesPhotoCard(
                    (mainViewModel.seasonsUiState as SeasonsUiState.Success).episodeResult,
                    seriesId,
                    selectedSeason,
                    onWatchedClicked = { episodeId ->
                        // Handle the logic for marking an episode as watched
                    }
                )
            is SeasonsUiState.Error -> Text(
                text = "Error: " +
                        "${(mainViewModel.seasonsUiState as SeasonsUiState.Error).errorMsg}"
            )
        }
    }
}


@Composable
fun DetailsPhotoCard (
    detailsResult: DetailsResult,
    seriesId: String,
    onSeasonSelected: (selectedSeason: Int) -> Unit
) {
    var selectedSeason by rememberSaveable {
        mutableStateOf(1)
    }

    var changeSeason by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            Text(
                text = detailsResult.name ?: "",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(vertical = 4.dp),
                textAlign = TextAlign.Center,
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original/${detailsResult.posterPath}")
                    .crossfade(true)
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp)
                    .padding(4.dp) // Add padding for better appearance
                    .align(Alignment.CenterHorizontally), // Center the image horizontally
            )

            Text(
                text = detailsResult.overview ?: "",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp),
                textAlign = TextAlign.Left,
            )

            Text(
                text = "Genres: ${detailsResult.genres.joinToString(", ") { it.name }}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 2.dp),
                textAlign = TextAlign.Left,
            )
            Text(
                text = "First Air Date: ${detailsResult.firstAirDate}, Last Air Date: ${detailsResult.lastAirDate}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 2.dp),
                textAlign = TextAlign.Left,
            )

            SpinnerSample(
                detailsResult.numberOfSeasons,
                selectedSeason,
                onSelectionChanged = {
                    selectedSeason = it
                    changeSeason = true
                    onSeasonSelected(it)
                    },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun SpinnerSample(
    numberOfSeasons: Int,
    preselectedSeason: Int,
    onSelectionChanged: (selectedSeason: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedSeason by remember { mutableStateOf(preselectedSeason) }
    var expanded by remember { mutableStateOf(false) }

    val seasonsList = (1..numberOfSeasons).toList()

    OutlinedCard(
        modifier = modifier.clickable {
            expanded = !expanded
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = "Season $selectedSeason",
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
            Icon(Icons.Outlined.ArrowDropDown, contentDescription = null, modifier = Modifier.padding(4.dp))
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                seasonsList.forEach { season ->
                    DropdownMenuItem(
                        onClick = {
                            selectedSeason = season
                            expanded = false
                            onSelectionChanged(selectedSeason)
                        },
                        text = {
                            Text(
                                text = "Season $season",
                                modifier = Modifier
                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun EpisodesPhotoCard (
    episodeResult: EpisodeResult,
    seriesId: String,
    seasonNumber: Int,
    onWatchedClicked: (episodeId: Int) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
        Column() {
            Text(
                text = "Season ${seasonNumber.toString()}",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 2.dp),
                textAlign = TextAlign.Left,
            )
            LazyColumn(modifier = Modifier.fillMaxSize()){
                itemsIndexed(episodeResult.episodes.orEmpty()) { index, item ->
                    item?.let { episode ->
                        EpisodeCard(episode = episode, onWatchedClicked = onWatchedClicked)
                    }
                }
            }
        }
    }
}

@Composable
fun EpisodeCard(
    episode: Episode,
    onWatchedClicked: (episodeId: Int) -> Unit
) {
    var isWatched = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = episode.name ?: "Unknown Episode",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 2.dp),
            textAlign = TextAlign.Left,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = episode.overview ?: "No overview available",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 4.dp),
                textAlign = TextAlign.Left,
            )
            IconButton(
                onClick = {
                    isWatched.value = !isWatched.value
                    onWatchedClicked(episode.id ?: 0)
                }
            ) {
                Icon(
                    imageVector = if(isWatched.value) Icons.Outlined.Add else Icons.Outlined.Done,
                    contentDescription = if (isWatched.value) "Watched" else "Mark as watched"
                )
            }
        }
    }
}



