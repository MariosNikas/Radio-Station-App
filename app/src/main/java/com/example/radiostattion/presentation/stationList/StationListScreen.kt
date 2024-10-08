package com.example.radiostattion.presentation.stationList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.radiostattion.domain.models.RadioStation

@Composable
fun StationListScreen(
    viewModel: StationListScreenViewModel = hiltViewModel(),
    radioStations: List<RadioStation>,
    onNavigateToNextScreen: (RadioStation) -> Unit,
) {
    val favouriteIds by viewModel.favourites.collectAsState(emptyList())

    var searchText by remember { mutableStateOf("") }
    var selectedTabIndex by remember { mutableStateOf(0) }

    val filteredStations = if (searchText.isBlank()) {
        radioStations
    } else {
        radioStations.filter { it.title.contains(searchText, ignoreCase = true) }
    }

    val displayedStations = when (selectedTabIndex) {
        1 -> filteredStations.filter { favouriteIds.contains(it.station_id) }
        else -> filteredStations
    }


    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        TabRow(selectedTabIndex = selectedTabIndex) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
                text = {
                    Text(
                        text = "All Stations",
                        fontSize = TextUnit(18f, type = TextUnitType.Sp),
                    )
                }
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
                text = {
                    Text(
                        text = "Favorites",
                        fontSize = TextUnit(18f, type = TextUnitType.Sp),
                    )
                }
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(displayedStations) { station ->
                StationCard(
                    station = station,
                    onClick = onNavigateToNextScreen,
                    onToggleFavorite = {
                        viewModel.onFavouriteClicked(station.station_id)
                    },
                    favourite = favouriteIds.contains(station.station_id)
                )
            }
        }
    }
}
