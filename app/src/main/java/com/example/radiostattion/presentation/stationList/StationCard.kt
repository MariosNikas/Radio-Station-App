package com.example.radiostattion.presentation.stationList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.radiostattion.domain.models.RadioStation
import com.example.radiostattion.presentation.uiKit.FavoriteButton

@Composable
fun StationCard(
    modifier: Modifier = Modifier,
    station: RadioStation,
    onClick: (RadioStation) -> Unit,
    favourite: Boolean,
    onToggleFavorite: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(station) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp),
            content = {
                AsyncImage(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(end = 16.dp),
                    model = station.logo,
                    contentDescription = "logo"
                )
                Text(
                    text = station.title,
                    fontSize = TextUnit(18f, type = TextUnitType.Sp),
                )
                Spacer(Modifier.weight(1f))
                FavoriteButton(
                    onToggleFavorite = { onToggleFavorite() },
                    isFavourite = favourite
                )
            }
        )
    }
}