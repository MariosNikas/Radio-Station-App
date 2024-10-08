package com.example.radiostattion.presentation.splash

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.radiostattion.domain.models.RadioStation
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel(),
    goToNextScreen: (List<RadioStation>) -> Unit
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val stations by viewModel.radioStations.collectAsState()
    val pulsingInfiniteTransition = rememberInfiniteTransition(label = "PulsingInfiniteTransition")

    val animationScale by pulsingInfiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "scale"
    )

    LaunchedEffect(Unit) {
        while (isLoading) {
            delay(100)
        }
        goToNextScreen(stations)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 24.dp)
                .align(Alignment.CenterHorizontally)
                .scale(animationScale),
            text = "RADIO STATIONS",
        )
    }
}