package io.fixmyride.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import io.fixmyride.models.Vehicle
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements

@Composable
fun ViewVehicleScreen(
    navCtrl: NavController,
    vehicle: Vehicle,
) {
    Surface(
        color = ColorPalette.background,
        modifier = Modifier.padding(horizontal = Measurements.screenPadding),
    ) {
        Column {
            // TODO replace registration number with text with opacity 0.5
            Spacer(Modifier.height(Measurements.screenPadding))

            UniversalHeader(
                caption = "Vehicle model here", /* + registration number */
                navCtrl = navCtrl,
            )
        }

        FloatingButton(
            color = ColorPalette.primary,
            icon = Icons.Rounded.Edit,
            alignment = Alignment.BottomEnd,
        ) { navCtrl.navigate("/edit-vehicle/$vehicle") }

    }
}