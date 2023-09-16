package io.fixmyride.utils

import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import io.fixmyride.database.DatabaseManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDate

/** Provides functions for importing and exporting data to/from files */
object DataExchange {
    /** Used for importing data from previously exported file */
    suspend fun importData() {
        // TODO
        val gson = Gson()
    }

    /** Used for exporting data to a file */
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun exportData() {
        val vehicleData = DatabaseManager.getInstance().dao.getData()
        val gson = Gson()
        val extStorage = Environment.getExternalStorageDirectory()
        val fileName = "FixMyRide_${LocalDate.now()}.json"
        val file = File(extStorage, fileName)

        try {
            val dataToConvert = mutableListOf<Map<String, Any?>>()
            for (v in vehicleData) dataToConvert.add(v.toMap())
            val json = gson.toJson(dataToConvert)

            withContext(Dispatchers.IO) {
                val fos = FileOutputStream(file)
                fos.write(json.toByteArray())
                fos.close()
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}