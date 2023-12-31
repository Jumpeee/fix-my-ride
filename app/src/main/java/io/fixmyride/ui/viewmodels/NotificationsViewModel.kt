package io.fixmyride.ui.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import io.fixmyride.data.models.Notification
import io.fixmyride.data.repositories.VehicleRepository
import io.fixmyride.utils.NotificationChecker

class NotificationsViewModel(
    val navController: NavController,
    private val repo: VehicleRepository,
) : ViewModel() {
    private val _notifications = mutableStateOf<List<Notification>>(emptyList())

    val notifications: State<List<Notification>> = _notifications

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun loadNotifications() {
        val allVehicles = repo.getVehicles()
        val tempNotifications = mutableListOf<Notification>()

        for (v in allVehicles) {
            val expirations = NotificationChecker.checkForNotifications(v)
            if (expirations.isNotEmpty()) {
                tempNotifications.add(
                    Notification(
                        repo.getVehicleById(v.id),
                        expirations,
                    ),
                )
            }
        }

        _notifications.value = tempNotifications
    }
}