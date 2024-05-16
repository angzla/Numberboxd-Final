package com.example.weather.ui.screen


import android.util.Log
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weather.R
import com.example.weather.data.Result
import com.example.weather.data.TVresult
import com.example.weather.data.cityItem
import com.example.weather.data.showCategory
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityScreen(
    cityViewModel: CityViewModel = viewModel(),
    onNavigateToMain: (String) -> Unit,
){

    var city by rememberSaveable {
        mutableStateOf("")
    }
    var cityErrorState by rememberSaveable { mutableStateOf(false) }

    var showAddDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var showEditDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var cityToEdit: cityItem? by rememberSaveable {
        mutableStateOf(null)
    }

    fun validate() {
        cityErrorState = city.isEmpty()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "NumberBoxd")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE784BA)
                ),
                actions = {
                    IconButton(onClick = {
                        cityViewModel.clearAllItems()
                    }) {
                        Icon(Icons.Filled.Delete, null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Filled.Add, "Add Show")
            }
        },
        content = {

            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Favorite Shows",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )


                LazyRow(modifier = Modifier.fillMaxSize()) {
                    items(cityViewModel.getFavoriteitems()) {
                        CityCard(
                            it,
                            onRemoveItem = { cityViewModel.removeItem(it) },
                            onEditItem = {
                                cityToEdit = it
                                showEditDialog = true
                            },
                            onNavigateToMain
                        )
                    }
                }

                Text(
                    text = "Currently Watching",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                LazyRow(modifier = Modifier.fillMaxSize()) {
                    items(cityViewModel.getCurrentlyWatching()) {
                        CityCard(
                            it,
                            onRemoveItem = { cityViewModel.removeItem(it) },
                            onEditItem = {
                                cityToEdit = it
                                showEditDialog = true
                            },
                            onNavigateToMain
                        )
                    }
                }

                Text(
                    text = "Watchlist",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                LazyRow(modifier = Modifier.fillMaxSize()) {
                    items(cityViewModel.getWatchlist()) {
                        CityCard(
                            it,
                            onRemoveItem = { cityViewModel.removeItem(it) },
                            onEditItem = {
                                cityToEdit = it
                                showEditDialog = true
                            },
                            onNavigateToMain
                        )
                    }
                }
            }
            if (showAddDialog) {
                AddCityDialog(
                    cityViewModel,
                    onDismiss = {
                        showAddDialog = false
                    }
                )
            }

            if (showEditDialog) {
                AddCityDialog(
                    cityViewModel,
                    onDismiss = {
                        showEditDialog = false
                    },
                    cityToEdit
                )
            }


        }
    )
}

@Composable
fun AddCityDialog(
    cityViewModel: CityViewModel,
    onDismiss: () -> Unit,
    cityToEdit: cityItem? = null,
    mainViewModel: MainViewModel = hiltViewModel(),
){
    var city by rememberSaveable {
        mutableStateOf(cityToEdit?.city ?:"")
    }
    var showRating by rememberSaveable {
        mutableStateOf(cityToEdit?.showRating ?:"")
    }

    var ID by rememberSaveable {
        mutableStateOf(cityToEdit?.ID ?:"")
    }

    var posterPath by rememberSaveable {
        mutableStateOf(cityToEdit?.posterPath ?:"")
    }


    var isFavorite by rememberSaveable {
        mutableStateOf(cityToEdit?.isFavorite ?:true)
    }

    var cityErrorState by rememberSaveable { mutableStateOf(false) }

    var selectedCategory by rememberSaveable {
        mutableStateOf(
            cityToEdit?.showcategory ?:
        showCategory.Currently_Watching)
    }

//    var searchResults by rememberSaveable { mutableStateOf(emptyList<String>()) }
//    var selectedSearchResult by rememberSaveable { mutableStateOf("") }

    fun validate() {
        cityErrorState = city.isEmpty()
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
        ){
            Column(
                Modifier.padding(16.dp)
            ) {

                Column(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    OutlinedTextField(
                        value = city,
                        onValueChange = {
                            city = it
                            validate()
                        },
                        label = { Text(text = "Enter Show Name") },
                        isError = cityErrorState
                    )
                    Button(
                        onClick = {
                            mainViewModel.getWeather("0ee8e741a484dbcebd23c922f5a8a778", city, "false", "en-US", "1")
                    }

                    ) {
                    Text(text = "Search")
                }

                    when (val currentState = mainViewModel.weatherUiState) {
                        is WeatherUiState.Init -> {
                            // Handle initialization state
                        }
                        is WeatherUiState.Loading -> {
                            CircularProgressIndicator()
                        }
                        is WeatherUiState.Success -> {
                            val tvResult = currentState.tvResult
                            DropDownCard(tvResult,
                                onItemSelected = { index, item: Result ->
                                    ID = item.id.toString()
                                    posterPath = item.posterPath
                                    city = item.name
                                })
//
//                            ID = tvResult.results.getOrNull(0)?.id?.toString() ?: ""
//                            posterPath = tvResult.results.getOrNull(0)?.posterPath?: ""
                        }
                        is WeatherUiState.Error -> {
                            Text(
                                text = "Error: ${currentState.errorMsg}"
                            )
                        }
                    }

                }

                RatingBar(
                    rating = showRating.toIntOrNull() ?: 0,
                    onRatingChanged = { ratingValue ->
                        showRating = ratingValue.toString()
                    }
                )

                val inputErrorState = cityErrorState

        if (inputErrorState) {
            Text(
                text = when {
                    cityErrorState -> "show cannot be empty"
                    else -> ""
                },
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
// FOR CHECKING IF FAVORITE OR NOT
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                Checkbox(
                    checked = isFavorite,
                    onCheckedChange = {
                        isFavorite = it
                    }
                )
                    Text(text = "Favorite")
                }


                //FOR ADDING SHOWS TO LIST

                SpinnerSample(
                    list = showCategory.values().toList(),
                    preselected = selectedCategory,
                    onSelectionChanged = { selectedCategory = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )

        Row {

            Button(
                enabled = !inputErrorState,
                onClick = {
                    if (city.isNotEmpty()) {
                        if (cityToEdit == null) {
                            cityViewModel.addToCityList(
                                cityItem(
                                    city = city,
                                    isFavorite = isFavorite,
                                    showRating = showRating,
                                    showcategory = selectedCategory,
                                    ID = ID,
                                    posterPath = posterPath
                                )
                            )
                        } else {
                            var originalCity = cityToEdit.copy()
                            var editedcity = cityItem(
                                city = city,
                                isFavorite = isFavorite,
                                showRating = showRating,
                                showcategory = selectedCategory,
                                ID = ID,
                                posterPath = posterPath
                                )
                            cityViewModel.editItem(originalCity, editedcity)
                        }
                    }
                onDismiss()}) {
                Text(text = stringResource(R.string.save))
            }

            TextButton(onClick = { onDismiss() }) {
                Text(text = "cancel")
            }
        }

    }
    }
    }
}


@Composable
fun CityCard(
    cityItem: cityItem,
    onRemoveItem: () -> Unit = {},
    onEditItem: (cityItem) -> Unit = {},
    onNavigateToMain: (String) -> Unit,
    cityViewModel: CityViewModel = viewModel(),

) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = {
                onNavigateToMain(
                    cityViewModel.getID(cityItem)
                )
            }) {
                Text(text = cityItem.city)
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original/${cityItem.posterPath}")
                    .crossfade(true)
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
                    .clickable {
                        onNavigateToMain(
                            cityViewModel.getID(cityItem)
                        )
                    }
            )
            Spacer(modifier = Modifier.fillMaxSize(0.55f))
            Spacer(modifier = Modifier.width(10.dp))

            RatingBar(
                rating = cityItem.showRating.toIntOrNull() ?: 0,
                onRatingChanged = { newRating ->
                    cityViewModel.updateShowRating(cityItem, newRating.toString())
                }
            )

            Row(){
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier.clickable {
                        onEditItem(cityItem)
                    },
                    tint = Color.Blue
                )
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                modifier = Modifier.clickable {
                    onRemoveItem()
                },
                tint = Color.Red
            )
            }
        }
    }
}

@Composable
fun SpinnerSample(
    list: List<showCategory>,
    preselected: showCategory,
    onSelectionChanged: (myData: showCategory) -> Unit,
    modifier: Modifier = Modifier
){
    var selected by remember { mutableStateOf(preselected) }
    var expanded by remember { mutableStateOf(false) } // initial value
    OutlinedCard(
        modifier = modifier.clickable {
            expanded = !expanded
        } ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top,
        ){ Text(
            text = selected.name,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
            Icon(Icons.Outlined.ArrowDropDown, null, modifier = Modifier.padding(8.dp))
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }, modifier = Modifier.fillMaxWidth()
            ){
                list.forEach { listEntry ->
                    DropdownMenuItem( onClick = {
                        selected = listEntry
                        expanded = false
                        onSelectionChanged(selected)
                    },
                        text = {
                            Text(
                                text = listEntry.name,
                                modifier = Modifier
                            ) },
                    )
                } }
        }}
}

@Composable
fun DropDownCard (
    tVresult: TVresult,
    onItemSelected: (Int, Result) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .heightIn(max = 200.dp) // Set maximum height here
                    .fillMaxWidth()
            ){
                LazyColumn {
                    itemsIndexed(tVresult.results) { index, item ->
                        Text(
                            text = item.name,
                            modifier = Modifier.clickable { onItemSelected(index, item) }
                        )
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://image.tmdb.org/t/p/original/${item.posterPath}")
                            .crossfade(true)
                            .build(),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(100.dp)
                            .height(150.dp)
                            .clickable { onItemSelected(index, item) }
                    )
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }
    }
}


@Composable
fun RatingBar(
    rating: Int,
    onRatingChanged: (Int) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        for (i in 1..5) {
            val dim = rating >= i && rating < i + 3
            val starIcon = if (dim) Icons.Outlined.Star else Icons.Filled.Star
            Icon(
                imageVector = starIcon,
                contentDescription = null,
                tint = if (rating >= i) Color.Yellow else Color.Gray,
                modifier = Modifier.clickable { onRatingChanged(i) }
            )
        }
    }
}




