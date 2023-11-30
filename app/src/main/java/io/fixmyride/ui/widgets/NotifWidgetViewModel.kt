package io.fixmyride.ui.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import io.fixmyride.data.enums.NotificationType
import io.fixmyride.data.repositories.VehicleRepository
import io.fixmyride.utils.NotificationChecker
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.O)
class NotifWidgetViewModel(
    private val repo: VehicleRepository,
) : ViewModel() {
    var expirations = 0
        private set

    var warnings = 0
        private set

    init {
        runBlocking {
            val vehicles = repo.getVehicles()

            for (v in vehicles) {
                for (n in NotificationChecker.checkForNotifications(v)) {
                    when (n) {
                        NotificationType.TPL_EXPIRED -> expirations++
                        NotificationType.CI_EXPIRED -> expirations++
                        NotificationType.INSPECTION_EXPIRED -> expirations++

                        NotificationType.TPL_ABOUT_TO_EXPIRE -> warnings++
                        NotificationType.CI_ABOUT_TO_EXPIRE -> warnings++
                        NotificationType.INSPECTION_UPCOMING -> warnings++
                    }
                }
            }
        }
    }
}