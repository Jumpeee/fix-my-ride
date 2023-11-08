package io.fixmyride.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.fixmyride.data.models.Vehicle
import io.fixmyride.utils.DateConverter

@Database(
    version = 1,
    entities = [Vehicle::class],
)
@TypeConverters(DateConverter::class)
abstract class Database : RoomDatabase() {
    abstract val dao: DatabaseDao
}