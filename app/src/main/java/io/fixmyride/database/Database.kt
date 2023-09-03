package io.fixmyride.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.fixmyride.models.Notification
import io.fixmyride.models.Vehicle

@Database(
    version = 1,
    entities = [
        Vehicle::class,
        Notification::class,
    ]
)
abstract class Database : RoomDatabase() {
    abstract val dao: DatabaseDao
}