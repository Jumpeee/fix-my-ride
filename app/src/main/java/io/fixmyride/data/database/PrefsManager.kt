package io.fixmyride.data.database

import android.content.Context
import android.content.SharedPreferences

/** Provides access to SharedPreferences in a more convenient way.
 *
 * List of all keys:
 * - "notifications_enabled"
 * - "notifications_days"
 * */
object PrefsManager {
    private lateinit var prefs: SharedPreferences

    fun initialize(context: Context) {
        prefs = context.getSharedPreferences("fixmyride-prefs", Context.MODE_PRIVATE)
    }

    fun getInstance(): SharedPreferences = prefs
}