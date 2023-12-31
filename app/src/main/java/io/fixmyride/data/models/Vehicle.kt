package io.fixmyride.data.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import io.fixmyride.utils.DateConverter
import java.time.LocalDate

@Entity(tableName = "vehicles")
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val model: String,
    val registration: String,

    @TypeConverters(DateConverter::class)
    val tplInsuranceExpiry: LocalDate,

    @TypeConverters(DateConverter::class)
    val collisionInsuranceExpiry: LocalDate? = null,

    @TypeConverters(DateConverter::class)
    val nextInspectionDate: LocalDate,
) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        val EMPTY = Vehicle(
            model = "",
            registration = "",
            tplInsuranceExpiry = LocalDate.MIN,
            collisionInsuranceExpiry = null,
            nextInspectionDate = LocalDate.MIN,
        )
    }
}