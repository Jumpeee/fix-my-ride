package io.fixmyride.models

import io.fixmyride.enums.NotificationType

data class Notification(
    val id: String,
    val vehicle: Vehicle,
    val type: NotificationType,
)