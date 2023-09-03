package io.fixmyride.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
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
import io.fixmyride.ui.theme.Typing

@Composable
fun SettingsScreen(navCtrl: NavController) {
    Surface(
        color = ColorPalette.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Measurements.screenPadding),
    ) {
        val scrollState = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState),
        ) {
            Spacer(Modifier.height(Measurements.screenPadding))

            UniversalHeader(
                caption = stringResource(R.string.settings),
                navCtrl = navCtrl,
            )

            AllOptions()

            Spacer(Modifier.height(20.dp))
            AuthorInfo()

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
    ) { /* TODO make days input */ }

    OptionButton(
        icon = Icons.Rounded.ArrowForward,
        name = stringResource(R.string.import_data),
        buttonText = stringResource(R.string.import_button),
        description = stringResource(R.string.import_data_from_an_existing_file),
        iconRotate = 90f,
    ) { /* TODO importing data */ }

    OptionButton(
        icon = Icons.Rounded.ArrowBack,
        name = stringResource(R.string.export_data),
        buttonText = stringResource(R.string.export_button),
        description = stringResource(R.string.export_data_to_a_file),
        iconRotate = 90f,
    ) { /* TODO exporting data */ }
}

@Composable
private fun AuthorInfo() {
    val uriOpener = LocalUriHandler.current

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            stringResource(R.string.made_by),
            style = Typing.descriptionBody,
        )
        Text(
            " Jumpee ",
            style = Typing.descriptionBody.copy(color = ColorPalette.primary),
            modifier = Modifier.clickable { uriOpener.openUri("https://github.com/Jumpeee") }
        )
        Icon(
            Icons.Rounded.Favorite,
            contentDescription = "Heart",
            tint = ColorPalette.lightRed,
            modifier = Modifier.size(14.dp),
        )
    }
}