package io.fixmyride.ui.widgets

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text
import io.fixmyride.ui.theme.ColorPalette

private object ExpirationsWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {

        // In this method, load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.
        provideContent {
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(color = ColorPalette.background),
            ) {
                Text("Sup'")
            }
        }
    }

}

object ExpirationsReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = ExpirationsWidget
}
