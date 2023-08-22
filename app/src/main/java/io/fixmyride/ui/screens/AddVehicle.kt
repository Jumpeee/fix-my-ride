package io.fixmyride.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.addvehicle.DateField
import io.fixmyride.ui.components.addvehicle.FormField
import io.fixmyride.ui.components.addvehicle.Thumbnail
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements

@Composable
fun AddVehicleScreen(navCtrl: NavController) {
    Surface(
        color = ColorPalette.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = Measurements.screenPadding,
                start = Measurements.screenPadding,
                end = Measurements.screenPadding,
            ),
    ) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            UniversalHeader(
                caption = stringResource(R.string.add_vehicle),
                navCtrl = navCtrl,
            )

            Thumbnail()

            FormField(
                caption = stringResource(R.string.addvehicle_model_headline),
                placeholder = stringResource(R.string.addvehicle_model_placeholder),
            )

            FormField(
                caption = stringResource(R.string.addvehicle_registration_number_headline),
                placeholder = "DWR 6004H",
            )

            DateField(
                caption = "TPL insurance expiry date",
            )

            Spacer(Modifier.height(80.dp))
        }
    }
}