package com.example.radiostattion.presentation.stationDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.radiostattion.domain.models.RadioStation
import com.example.radiostattion.presentation.uiKit.FavoriteButton


@Composable
fun StationDetailsScreen(
    viewModel: StationDetailsScreenViewModel = hiltViewModel(),
    station: RadioStation
) {
    LaunchedEffect(Unit) {
        viewModel.initialise(station.station_id)
    }

    val isFavorite by viewModel.isFavorite.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 24.dp),
            model = station.logo,
            contentDescription = station.title
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = station.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            FavoriteButton(
                onToggleFavorite = { viewModel.toggleFavourite(station.station_id) },
                isFavourite = isFavorite
            )
        }
        Text(text = station.description)
        Spacer(Modifier.weight(1f))
        Text(
            text = "ID: ${station.station_id}",
            fontSize = 12.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}