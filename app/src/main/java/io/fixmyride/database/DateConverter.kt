package io.fixmyride.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate

object DateConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDate(date: String): LocalDate = LocalDate.parse(date.replace(".", "-"))

    @TypeConverter
    fun fromLocalDate(date: LocalDate): String = date.toString().replace("-", ".")
}