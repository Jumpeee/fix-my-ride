package io.fixmyride.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.dialogs.SingleValueDialog
import io.fixmyride.ui.components.notifications.AuthorInfo
import io.fixmyride.ui.components.settings.Option
import io.fixmyride.ui.components.settings.ValueBox
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.viewmodels.SettingsViewModel


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun SettingsScreen(
    notificationsViewModel: SettingsViewModel.NotificationsViewModel,
) {
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
                navCtrl = notificationsViewModel.navController,
            )

            AllOptions(
                notificationsViewModel = notificationsViewModel,
            )
            Spacer(Modifier.height(20.dp))

            AuthorInfo()
            Spacer(Modifier.height(100.dp))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
private fun AllOptions(
    notificationsViewModel: SettingsViewModel.NotificationsViewModel,
) {
    Option(
        icon = Icons.Rounded.Email,
        name = stringResource(R.string.notifications),
        description = stringResource(R.string.settings_notifications_desc),
    ) {
        val daysString = stringResource(R.string.days)
        LaunchedEffect(Unit) { notificationsViewModel.loadDays(daysString) }

        Column {
            Spacer(Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                ValueBox(
                    isSelected = notificationsViewModel.selectedIndex.intValue == 0,
                    value = "7",
                ) { notificationsViewModel.saveFixedDaysAndUpdateSelectedIndex(it!!, 0) }

                ValueBox(
                    isSelected = notificationsViewModel.selectedIndex.intValue == 1,
                    value = "14",
                ) { notificationsViewModel.saveFixedDaysAndUpdateSelectedIndex(it!!, 1) }

                ValueBox(
                    isSelected = notificationsViewModel.selectedIndex.intValue == 2,
                    value = notificationsViewModel.customBoxCaption.value,
                ) { notificationsViewModel.toggleSingleValueDialogAndUpdateSelectedIndex(it) }

                if (notificationsViewModel.showSingleValueDialog.value) {
                    SingleValueDialog(
                        headline = stringResource(R.string.number_of_days),
                        placeholderText = stringResource(R.string.number_of_days),
                        keyboardType = KeyboardType.Number,
                    ) { notificationsViewModel.saveCustomDays(it) }
                }
            }
        }
    }
}
