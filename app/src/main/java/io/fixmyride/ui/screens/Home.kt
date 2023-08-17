package io.fixmyride.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.fixmyride.ui.components.home.AddVehicleButton
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.components.home.Header
import io.fixmyride.ui.components.home.NavBar
import io.fixmyride.ui.components.home.ScrollToTopButton
import io.fixmyride.ui.components.home.VehicleList
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navCtrl: NavHostController) {
    Surface(
        color = ColorPalette.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = Measurements.screenPadding,
                end = Measurements.screenPadding,
            ),
    ) {
        val scrollState = rememberScrollState()
        Box {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize(),
            ) {
                Spacer(Modifier.height(Measurements.screenPadding))

                Header(navCtrl)
                Spacer(Modifier.height(20.dp))

                AddVehicleButton(navCtrl)
                Spacer(Modifier.height(20.dp))

                VehicleList()
                Spacer(Modifier.height(40.dp))
            }

            NavBar(scrollState, navCtrl)

            val coroutineScope = rememberCoroutineScope()
            ScrollToTopButton(scrollState) {
                coroutineScope.launch {
                    scrollState.animateScrollTo(
                        0,
                        animationSpec = tween(
                            durationMillis = 1000,
                            easing = FastOutSlowInEasing,
                        )
                    )
                }
            }
        }
    }
}