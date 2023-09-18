package io.fixmyride.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.dialogs.SingleValueDialog
import io.fixmyride.ui.components.settings.Option
import io.fixmyride.ui.components.settings.OptionButton
import io.fixmyride.ui.components.settings.ValueBox
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing
import io.fixmyride.utils.DataExchange
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.R)
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
            navCtrl.popBackStack()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
private fun AllOptions() {
    val coroutineScope = rememberCoroutineScope()
    Option(
        icon = Icons.Rounded.Email,
        name = stringResource(R.string.notifications),
        description = stringResource(R.string.settings_notifications_desc),
    ) {
        Column {
            Spacer(Modifier.height(10.dp))
            val selectedIndex = remember { mutableIntStateOf(0) }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                ValueBox(
                    isSelected = selectedIndex.intValue == 0,
                    value = "7",
                ) {
                    selectedIndex.intValue = 0
                }

                ValueBox(
                    isSelected = selectedIndex.intValue == 1,
                    value = "14",
                ) {
                    selectedIndex.intValue = 1
                }

                val showSingleValueDialog = remember { mutableStateOf(false) }
                ValueBox(
                    isSelected = selectedIndex.intValue == 2,
                    value = ". . ."
                ) {
                    selectedIndex.intValue = 2
                    if (it == null) {
                        showSingleValueDialog.value = true
                    }
                }

                if (showSingleValueDialog.value) {
                    SingleValueDialog(
                        headline = stringResource(R.string.number_of_days),
                        placeholderText = stringResource(R.string.number_of_days),
                        keyboardType = KeyboardType.Number,
                    ) {

                    }
                }
            }
        }
    }

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
    ) {
        coroutineScope.launch {
            DataExchange.exportData()
        }
    }
}

@Composable
private fun AuthorInfo() {
    val uriOpener = LocalUriHandler.current

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            stringResource(R.string.made_by),
            style = Typing.descriptionBody,
        )
        Text(" Jumpee ",
            style = Typing.descriptionBody.copy(color = ColorPalette.primary),
            modifier = Modifier.clickable { uriOpener.openUri("https://github.com/Jumpeee") })
        Icon(
            Icons.Rounded.Favorite,
            contentDescription = "Heart",
            tint = ColorPalette.lightRed,
            modifier = Modifier.size(14.dp),
        )
    }
}
