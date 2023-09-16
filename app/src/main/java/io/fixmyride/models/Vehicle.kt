package io.fixmyride.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val model: String,
    val registration: String,
    val tplInsuranceExpiry: String, // e.g. 2005.05.16
    val collisionInsuranceExpiry: String? = null, // e.g. 2005.05.16
    val nextInspectionDate: String,
) {
    fun toMap() = mapOf(
        "id" to id,
        "model" to model,
        "registration" to registration,
        "tplInsuranceExpiry" to tplInsuranceExpiry,
        "collisionInsuranceExpiry" to collisionInsuranceExpiry,
        "nextInspectionDate" to nextInspectionDate,
    )
    companion object {
        val EMPTY = Vehicle(
            model = "",
            registration = "",
            tplInsuranceExpiry = "",
            collisionInsuranceExpiry = null,
            nextInspectionDate = "",
        )
    }
}