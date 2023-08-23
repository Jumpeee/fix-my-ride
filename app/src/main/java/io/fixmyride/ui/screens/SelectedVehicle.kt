package io.fixmyride.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements

@Composable
fun SelectedVehicleScreen(navCtrl: NavController) {
    Surface(
        color = ColorPalette.background,
        modifier = Modifier.padding(
            top = Measurements.screenPadding,
            start = Measurements.screenPadding,
            end = Measurements.screenPadding,
        ),
    ) {
        Column {
            // TODO replace registration number with text with opacity 0.5
            UniversalHeader(
                caption = "Vehicle model here" /* + registration number */,
                navCtrl = navCtrl,
            )
        }
    }
}