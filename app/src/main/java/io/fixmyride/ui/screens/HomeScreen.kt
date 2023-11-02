package io.fixmyride.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.ResultsBar
import io.fixmyride.ui.components.home.AddVehicleButton
import io.fixmyride.ui.components.home.Header
import io.fixmyride.ui.components.home.VehicleList
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    LaunchedEffect(Unit) { viewModel.loadVehicles() }

    val scrollState = rememberScrollState()
    Surface(
        color = ColorPalette.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Measurements.screenPadding),
    ) {
        Column(
            modifier = when (viewModel.vehicles.value.size) {
                0 -> Modifier
                else -> Modifier.verticalScroll(scrollState)
            },
        ) {
            Spacer(Modifier.height(Measurements.screenPadding))

            Header(viewModel.navController)
            Spacer(Modifier.height(20.dp))

            AddVehicleButton(viewModel.navController)
            Spacer(Modifier.height(20.dp))

            VehicleList(
                viewModel.navController,
                viewModel.vehicles.value
            ) { viewModel.setVehiclesOrder(it) }
            Spacer(Modifier.height(100.dp))
        }

        ResultsBar(
            results = viewModel.vehicles.value.size,
            alignment = Alignment.BottomStart,
            animationSpec = Measurements.scrollAnimation(delay = 125),
            scrollState = scrollState,
        )

        val coroutineScope = rememberCoroutineScope()
        FloatingButton(
            color = ColorPalette.primary,
            icon = Icons.Rounded.KeyboardArrowUp,
            alignment = Alignment.BottomEnd,
            scrollState = scrollState,
        ) {
            coroutineScope.launch {
                scrollState.animateScrollTo(
                    value = 0,
                    animationSpec = Measurements.scrollAnimation(duration = 1000)
                )
            }
        }
    }
}