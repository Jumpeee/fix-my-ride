package io.fixmyride.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements

@Composable
fun NotificationsScreen(navCtrl: NavController) {
    Surface(
        color = ColorPalette.background,
        modifier = Modifier.padding(
            top = Measurements.screenPadding,
            bottom = 0.dp,
            start = Measurements.screenPadding,
            end = Measurements.screenPadding,
        ),
    ) {
        Column {
            UniversalHeader(stringResource(R.string.notifications), navCtrl)

            Spacer(Modifier.height(10.dp))

            LazyColumn {
                items(10) {
                    // TODO
                }
            }
        }
    }
}