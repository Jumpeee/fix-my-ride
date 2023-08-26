package io.fixmyride.models

import java.time.LocalDate

data class Vehicle(
    val model: String,
    val registration: String,
    val tplInsurance: LocalDate,
    val comprehensiveInsurance: LocalDate,
    val inspectionDate: LocalDate,
)