package io.fixmyride.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val imagePath: String? = null,
    val model: String,
    val registration: String,
    val tplInsuranceExpiry: String, // e.g. 2005.05.16
    val collisionInsuranceExpiry: String? = null, // e.g. 2005.05.16
) {

    companion object {
        fun from(value: String): Vehicle {
            val splitString = value.split("|")
            return Vehicle(
                id = splitString[0].toInt(),
                model = splitString[1],
                registration = splitString[2],
                tplInsuranceExpiry = splitString[3],
                collisionInsuranceExpiry = splitString[4],
            )
        }
    }

    override fun toString(): String =
        "$id|$model|$registration|$tplInsuranceExpiry|$collisionInsuranceExpiry"
}