package io.fixmyride.models

import java.util.Date

data class Vehicle(
    val model: String,
    val registration: String,
    val tplInsurance: Date,
    val comprehensiveInsurance: Date,
    val inspectionDate: Date,
)