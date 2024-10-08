package com.example.radiostattion.presentation.uiKit

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    onToggleFavorite: () -> Unit,
    isFavourite: Boolean,
){
    val scale by animateFloatAsState(
        targetValue = if (isFavourite) 1.2f else 1f,
        animationSpec = tween(durationMillis = 300)
    )

    val iconColor by animateColorAsState(
        targetValue = if (isFavourite) Color.Red else Color.LightGray,
        animationSpec = tween(durationMillis = 300)
    )

    Icon(
        modifier = Modifier
            .clickable { onToggleFavorite() }
            .padding(end = 8.dp)
            .scale(scale),
        imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
        contentDescription = if (isFavourite) "Remove from favorites" else "Add to favorites",
        tint = iconColor
    )
}