package io.fixmyride.utils

import android.os.Build
import androidx.annotation.RequiresApi
import io.fixmyride.database.PrefsManager
import io.fixmyride.enums.NotificationType
import io.fixmyride.models.Vehicle
import java.time.LocalDate

/** Provides methods used for checking if vehicle's insurance (or inspection) is about to expire */
object NotificationChecker {
    @RequiresApi(Build.VERSION_CODES.O)
    fun checkForNotifications(v: Vehicle): List<NotificationType> {
        val expired = arrayListOf<NotificationType>()
        val notifDays = PrefsManager.getInstance().getInt("notifications_days", -1)

        fun dateDifference(date: LocalDate): Long {
            return date.minusDays(LocalDate.now().toEpochDay()).toEpochDay()
        }

        if (dateDifference(v.tplInsuranceExpiry) in 1..notifDays) {
            expired.add(NotificationType.TPL_EXPIRY)
        }

        if (v.collisionInsuranceExpiry != null) {
            if (dateDifference(v.collisionInsuranceExpiry) in 1..notifDays) {
                expired.add(NotificationType.CI_EXPIRY)
            }
        }

        if (dateDifference(v.nextInspectionDate) in 1..notifDays) {
            expired.add(NotificationType.UPCOMING_INSPECTION)
        }

        return expired
    }
}