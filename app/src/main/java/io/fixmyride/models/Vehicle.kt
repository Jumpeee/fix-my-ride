package io.fixmyride.models

import java.time.LocalDate

data class Vehicle(
    val id: String?,
    val model: String,
    val registration: String,
    val tplInsuranceExpiry: LocalDate,
    val collisionInsuranceExpiry: LocalDate,
    val inspectionDate: LocalDate,
) {
    override fun toString(): String =
        "$id $model $registration $tplInsuranceExpiry $collisionInsuranceExpiry $inspectionDate"
}