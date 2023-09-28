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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.database.PrefsManager
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.dialogs.SingleValueDialog
import io.fixmyride.ui.components.notifications.AuthorInfo
import io.fixmyride.ui.components.settings.Option
import io.fixmyride.ui.components.settings.ValueBox
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements


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
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
private fun AllOptions() {
    val prefs = PrefsManager.getInstance()
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
                    prefs.edit().putInt("notifications_days", it!!).apply()
                }

                ValueBox(
                    isSelected = selectedIndex.intValue == 1,
                    value = "14",
                ) {
                    selectedIndex.intValue = 1
                    prefs.edit().putInt("notifications_days", it!!).apply()
                }

                val showSingleValueDialog = remember { mutableStateOf(false) }
                val customBoxCaption = remember { mutableStateOf(". . .") }
                val daysString = stringResource(R.string.days)
                LaunchedEffect(Unit) {
                    when (val daysFromPrefs = prefs.getInt("notifications_days", -1)) {
                        7 -> selectedIndex.intValue = 0
                        14 -> selectedIndex.intValue = 1
                        -1 -> {
                            prefs.edit().putInt("notifications_days", 7).apply()
                            selectedIndex.intValue = 0
                        }

                        else -> {
                            selectedIndex.intValue = 2
                            customBoxCaption.value = "$daysFromPrefs $daysString"
                        }
                    }
                }

                ValueBox(
                    isSelected = selectedIndex.intValue == 2,
                    value = customBoxCaption.value,
                ) {
                    showSingleValueDialog.value = true
                    if (it == null) {
                        selectedIndex.intValue = 2
                    }
                }

                if (showSingleValueDialog.value) {
                    SingleValueDialog(
                        headline = stringResource(R.string.number_of_days),
                        placeholderText = stringResource(R.string.number_of_days),
                    ) {
                        showSingleValueDialog.value = false
                        if (it == null) {
                            selectedIndex.intValue = 0
                            prefs.edit().putInt("notifications_days", 7).apply()
                            customBoxCaption.value = ". . ."
                        } else {
                            prefs.edit().putInt("notifications_days", Integer.parseInt(it)).apply()
                            customBoxCaption.value = "$it"
                        }
                    }
                }
            }
        }
    }
}
