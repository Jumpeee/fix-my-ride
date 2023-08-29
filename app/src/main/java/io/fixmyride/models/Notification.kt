package io.fixmyride.models

import java.time.LocalDateTime

data class Notification(
    val id: String,
    val vehicle: Vehicle,
    val receivedAt: LocalDateTime,
)