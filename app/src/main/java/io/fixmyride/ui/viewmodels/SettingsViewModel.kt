package io.fixmyride.ui.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

sealed class SettingsViewModel : ViewModel() {
    class NotificationsViewModel(
        val navController: NavController,
        private val prefs: SharedPreferences
    ) {
        private val _selectedIndex = mutableIntStateOf(0)
        private val _showSingleValueDialog = mutableStateOf(false)
        private val _customBoxCaption = mutableStateOf(". . .")

        val selectedIndex: MutableIntState = _selectedIndex
        val showSingleValueDialog: State<Boolean> = _showSingleValueDialog
        val customBoxCaption: State<String> = _customBoxCaption

        fun loadDays(daysCaptionTranslation: String) {
            when (val daysFromPrefs = prefs.getInt("notifications_days", -1)) {
                7 -> _selectedIndex.intValue = 0
                14 -> _selectedIndex.intValue = 1
                -1 -> {
                    prefs.edit().putInt("notifications_days", 7).apply()
                    _selectedIndex.intValue = 0
                }

                else -> {
                    _selectedIndex.intValue = 2
                    _customBoxCaption.value = "$daysFromPrefs $daysCaptionTranslation"
                }
            }
        }

        /** Used when clicking button responsible for inputting custom amount of days. */
        fun toggleSingleValueDialogAndUpdateSelectedIndex(value: Int?) {
            _showSingleValueDialog.value = true
            if (value == null) _selectedIndex.intValue = 2
        }

        /** Used only for fields with 7 and 14 days. */
        fun saveFixedDaysAndUpdateSelectedIndex(days: Int, index: Int) {
            _selectedIndex.intValue = index
            prefs.edit().putInt("notifications_days", days).apply()
        }

        /** Used only for field which lets user provide custom amount of days. */
        fun saveCustomDays(days: String?) {
            _showSingleValueDialog.value = false
            if (days == null) {
                _selectedIndex.intValue = 0
                prefs.edit().putInt("notifications_days", 7).apply()
                _customBoxCaption.value = ". . ."
            } else {
                prefs.edit().putInt("notifications_days", Integer.parseInt(days)).apply()
                _customBoxCaption.value = "$days"
            }
        }
    }
}