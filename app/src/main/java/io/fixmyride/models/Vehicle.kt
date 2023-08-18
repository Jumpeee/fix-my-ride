package io.fixmyride.models

import java.util.Date

data class Vehicle(
    val model: String,
    val registration: String,
    val oc: Date,
    val ac: Date,
    val inspectionDate: Date,
)