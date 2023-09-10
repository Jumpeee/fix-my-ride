package io.fixmyride.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val imagePath: ByteArray? = null,
    val model: String,
    val registration: String,
    val tplInsuranceExpiry: String, // e.g. 2005.05.16
    val collisionInsuranceExpiry: String? = null, // e.g. 2005.05.16
    val nextInspectionDate: String,
) {
    companion object {
        fun empty(): Vehicle = Vehicle(
            imagePath = null,
            model = "",
            registration = "",
            tplInsuranceExpiry = "",
            collisionInsuranceExpiry = null,
            nextInspectionDate = "",
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vehicle

        if (id != other.id) return false
        if (imagePath != null) {
            if (other.imagePath == null) return false
            if (!imagePath.contentEquals(other.imagePath)) return false
        } else if (other.imagePath != null) return false
        if (model != other.model) return false
        if (registration != other.registration) return false
        if (tplInsuranceExpiry != other.tplInsuranceExpiry) return false
        if (collisionInsuranceExpiry != other.collisionInsuranceExpiry) return false
        if (nextInspectionDate != other.nextInspectionDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (imagePath?.contentHashCode() ?: 0)
        result = 31 * result + model.hashCode()
        result = 31 * result + registration.hashCode()
        result = 31 * result + tplInsuranceExpiry.hashCode()
        result = 31 * result + (collisionInsuranceExpiry?.hashCode() ?: 0)
        result = 31 * result + nextInspectionDate.hashCode()
        return result
    }
}