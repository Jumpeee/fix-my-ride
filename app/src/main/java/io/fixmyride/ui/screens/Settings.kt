package io.fixmyride.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.settings.Option
import io.fixmyride.ui.components.settings.OptionButton
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements

@Composable
fun SettingsScreen(navCtrl: NavController) {
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
        Column(Modifier.verticalScroll(scrollState)) {
            UniversalHeader(
                caption = stringResource(R.string.settings),
                navCtrl = navCtrl,
            )

            AllOptions()

            Spacer(Modifier.height(100.dp))
        }

        FloatingButton(
            color = ColorPalette.primary,
            icon = Icons.Rounded.Done,
            alignment = Alignment.BottomEnd,
            scrollState = null,
        ) {
            // TODO saving settings
        }
    }
}

@Composable
private fun AllOptions() {
    Option(
        icon = Icons.Rounded.Email,
        name = stringResource(R.string.notifications),
        description = stringResource(R.string.settings_notifications_desc),
    )

    Option(
        icon = Icons.Rounded.ArrowForward,
        name = stringResource(R.string.save_locally),
        description = stringResource(R.string.settings_save_locally_desc),
        iconRotate = 90f,
    )

    OptionButton(
        icon = Icons.Rounded.Refresh,
        name = stringResource(R.string.load_data),
        buttonText = stringResource(R.string.load),
        description = stringResource(R.string.settings_load_data_desc),
    ) {
        // TODO implement loading data from local storage
    }
}