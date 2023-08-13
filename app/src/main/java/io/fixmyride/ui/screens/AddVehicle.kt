package io.fixmyride.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements

@Composable
fun AddVehicleScreen(navCtrl: NavController) {
    Surface(
        color = ColorPalette.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(Measurements.screenPadding)
    ) {
        Column {
            UniversalHeader(caption = stringResource(R.string.add_vehicle), navCtrl = navCtrl)
        }
    }
}