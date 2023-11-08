package io.fixmyride.data.models

data class Notification(
    val relatedVehicle: Vehicle,
    val expirations: List<Int>,
)