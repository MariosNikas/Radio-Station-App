package com.example.radiostattion.presentation.navigation

import android.os.Bundle
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.radiostattion.domain.models.RadioStation
import com.example.radiostattion.presentation.navigation.NavParams.radioStationsParam
import com.example.radiostattion.presentation.navigation.NavParams.selectedStationParam
import com.example.radiostattion.presentation.navigation.NavRoutes.splashScreenRoute
import com.example.radiostattion.presentation.navigation.NavRoutes.stationDetailsScreenRoute
import com.example.radiostattion.presentation.navigation.NavRoutes.stationListScreenRoute
import com.example.radiostattion.presentation.splash.SplashScreen
import com.example.radiostattion.presentation.stationDetails.StationDetailsScreen
import com.example.radiostattion.presentation.stationList.StationListScreen

@Composable
fun PrimaryNavigator(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = splashScreenRoute
    ) {
        composable(
            route = splashScreenRoute,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            SplashScreen(
                goToNextScreen = { stations ->
                    val bundle = Bundle()
                    bundle.putParcelableArrayList(radioStationsParam, ArrayList(stations))

                    navController.navigate(
                        route = stationListScreenRoute,
                        args = bundle
                    )
                }
            )
        }
        composable(
            route = stationDetailsScreenRoute,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            val bundle = it.arguments
            val selectedStation =
                bundle?.getParcelable(selectedStationParam, RadioStation::class.java)

            if (selectedStation != null) {
                StationDetailsScreen(
                    station = selectedStation
                )
            } else {
                Text(text = "Something went wrong")
            }
        }

        composable(
            route = stationListScreenRoute,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            val bundle = it.arguments
            val stations =
                bundle?.getParcelableArrayList(radioStationsParam, RadioStation::class.java)
                    ?.toList()
            StationListScreen(
                radioStations = stations ?: emptyList(),
                onNavigateToNextScreen = { station ->
                    val bundle = Bundle()
                    bundle.putParcelable(selectedStationParam, station)

                    navController.navigate(
                        route = stationDetailsScreenRoute,
                        args = bundle
                    )
                }
            )
        }
    }
}

fun NavController.navigate(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val nodeId = graph.findNode(route = route)?.id
    if (nodeId != null) {
        navigate(nodeId, args, navOptions, navigatorExtras)
    }
}