package io.fixmyride.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val thumbnailPath: String? = null,
    val model: String,
    val registration: String,
    val tplInsuranceExpiry: String, // e.g. 2005.05.16
    val collisionInsuranceExpiry: String? = null, // e.g. 2005.05.16
) {

    val imagePath: String?
        get() = thumbnailPath?.replace("*^", "/")

    companion object {
        fun from(value: String): Vehicle {
            val splitString = value.split("|")
            return Vehicle(
                splitString[0].toInt(),
                splitString[1],
                splitString[2],
                splitString[3],
                splitString[4],
                splitString[5],
            )
        }
    }

    override fun toString(): String =
        "$id|${
            imagePath?.replace(
                "/",
                "*^"
            )
        }|$model|$registration|$tplInsuranceExpiry|$collisionInsuranceExpiry"
}