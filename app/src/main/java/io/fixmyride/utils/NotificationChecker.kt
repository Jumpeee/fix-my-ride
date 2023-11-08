package io.fixmyride.utils

import android.os.Build
import androidx.annotation.RequiresApi
import io.fixmyride.data.database.PrefsManager
import io.fixmyride.data.enums.NotificationType
import io.fixmyride.data.models.Vehicle
import java.time.LocalDate

/** Provides methods used for checking if vehicle's insurance (or inspection) is about to expire */
object NotificationChecker {
    @RequiresApi(Build.VERSION_CODES.O)
    fun checkForNotifications(v: Vehicle): List<Int> {
        val expired = arrayListOf<Int>()
        val notificationDays = PrefsManager.getInstance().getInt("notifications_days", -1)

        val tplDifference = dateDifference(v.tplInsuranceExpiry)
        when {
            tplDifference < 0 -> expired.add(NotificationType.TPL_EXPIRED)
            tplDifference in 1..notificationDays -> expired.add(NotificationType.TPL_ABOUT_TO_EXPIRE)
        }

        if (v.collisionInsuranceExpiry != null) {
            val ciDifference = dateDifference(v.collisionInsuranceExpiry)
            when {
                ciDifference < 0 -> expired.add(NotificationType.CI_EXPIRED)
                ciDifference in 1..notificationDays -> expired.add(NotificationType.CI_ABOUT_TO_EXPIRE)
            }
        }

        val inspectionDifference = dateDifference(v.nextInspectionDate)
        when {
            inspectionDifference < 0 -> expired.add(NotificationType.INSPECTION_EXPIRED)
            inspectionDifference in 1..notificationDays -> expired.add(NotificationType.INSPECTION_UPCOMING)
        }

        return expired
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun dateDifference(date: LocalDate): Long {
        return date.minusDays(LocalDate.now().toEpochDay()).toEpochDay()
    }
}