package io.fixmyride.database

import android.content.Context
import androidx.room.Room

object DatabaseManager {
    private lateinit var database: Database

    fun initialize(context: Context) {
        database = Room.databaseBuilder(
            context.applicationContext,
            Database::class.java, "fixmyride-db"
        ).fallbackToDestructiveMigration().build()
    }

    fun getInstance(): Database = database
}