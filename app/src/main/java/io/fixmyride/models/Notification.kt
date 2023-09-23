package io.fixmyride.models

import io.fixmyride.enums.NotificationType

data class Notification(
    val relatedVehicleId: Int,
    val expirations: List<NotificationType>,
)