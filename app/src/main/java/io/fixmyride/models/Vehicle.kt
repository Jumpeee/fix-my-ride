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
    val inspectionDate: String? = null, // e.g. 2005.05.16
) {
    override fun toString(): String =
        "$id $model $registration $tplInsuranceExpiry $collisionInsuranceExpiry $inspectionDate"
}