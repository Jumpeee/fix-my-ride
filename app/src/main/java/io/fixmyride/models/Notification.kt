package io.fixmyride.models

import java.time.LocalDateTime

data class Notification(
    val vehicle: Vehicle,
    val receivedAt: LocalDateTime,
)