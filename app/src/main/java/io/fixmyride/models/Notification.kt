package io.fixmyride.models

import java.util.Date

data class Notification(
    val vehicle: Vehicle,
    val receivedAt: Date,
)