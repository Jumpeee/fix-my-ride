package io.fixmyride.ui.widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.room.Room
import io.fixmyride.R
import io.fixmyride.data.database.Database
import io.fixmyride.data.database.PrefsManager
import io.fixmyride.data.repositories.VehicleRepositoryImpl

class NotifWidgetProvider : AppWidgetProvider() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        PrefsManager.initialize(context)
        
        val dao = Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "fixmyride-db",
        ).fallbackToDestructiveMigration().build().dao

        val vehicleRepo = VehicleRepositoryImpl(dao)
        val viewModel = NotifWidgetViewModel(vehicleRepo)

        for (id in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.notif_widget_layout)

            views.setViewVisibility(R.id.activeStats, View.GONE)

            if (viewModel.expirations != 0) {
                views.setViewVisibility(R.id.expirationStats, View.VISIBLE)
                views.setTextViewText(R.id.expirationStats, "${viewModel.expirations}")
            } else {
                views.setViewVisibility(R.id.expirationStats, View.GONE)
            }

            if (viewModel.warnings != 0) {
                views.setViewVisibility(R.id.warningStats, View.VISIBLE)
                views.setTextViewText(R.id.warningStats, "${viewModel.warnings}")
            } else {
                views.setViewVisibility(R.id.warningStats, View.GONE)
            }

            if (viewModel.expirations == 0 && viewModel.warnings == 0) {
                views.setViewVisibility(R.id.activeStats, View.VISIBLE)
            }

            appWidgetManager.updateAppWidget(id, views)
        }

    }
}