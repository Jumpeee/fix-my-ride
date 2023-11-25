package io.fixmyride.ui.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.fixmyride.data.enums.NotificationType
import io.fixmyride.data.models.Vehicle
import io.fixmyride.data.repositories.VehicleRepository
import io.fixmyride.utils.NotificationChecker

class NotifViewModel(
    private val repo: VehicleRepository,
) : ViewModel() {
    private val _vehicles = mutableStateOf(emptyList<Vehicle>())
    private val _expirations = mutableIntStateOf(0)
    private val _warnings = mutableIntStateOf(0)

    val vehicles: MutableState<List<Vehicle>> = _vehicles
    val expirations: MutableIntState = _expirations
    val warnings: MutableIntState = _warnings

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllVehicles() {
        _vehicles.value = repo.getVehicles()
        _expirations.intValue = getExpirations()
        _warnings.intValue = getWarnings()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getWarnings(): Int {
        var count = 0
        for (v in _vehicles.value) {
            for (n in NotificationChecker.checkForNotifications(v)) {
                when (n) {
                    NotificationType.TPL_ABOUT_TO_EXPIRE -> count++
                    NotificationType.CI_ABOUT_TO_EXPIRE -> count++
                    NotificationType.INSPECTION_UPCOMING -> count++
                }
            }
        }

        return count
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getExpirations(): Int {
        var count = 0
        for (v in _vehicles.value) {
            for (n in NotificationChecker.checkForNotifications(v)) {
                when (n) {
                    NotificationType.CI_EXPIRED -> count++
                    NotificationType.TPL_EXPIRED -> count++
                    NotificationType.INSPECTION_EXPIRED -> count++
                }
            }
        }

        return count
    }
}