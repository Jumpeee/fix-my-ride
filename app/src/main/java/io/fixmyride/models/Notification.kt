package io.fixmyride.models

data class Notification(
    val relatedVehicleId: Int,
    val expirations: List<Int>,
)