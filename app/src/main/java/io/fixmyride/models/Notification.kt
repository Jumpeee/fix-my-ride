package io.fixmyride.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.fixmyride.enums.NotificationType

@Entity
data class Notification(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val relatedVehicleId: Int,
    val type: NotificationType,
)