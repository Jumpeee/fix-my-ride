package io.fixmyride.ui.widgets

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontFamily
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.room.Room
import io.fixmyride.MainActivity
import io.fixmyride.data.database.Database
import io.fixmyride.data.repositories.VehicleRepositoryImpl
import io.fixmyride.ui.theme.ColorPalette

class NotifWidget : GlanceAppWidget() {
    private enum class DisplayType {
        EXPIRATION,
        WARNING,
        ACTIVE,
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val dao = Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "fixmyride-db",
        ).fallbackToDestructiveMigration().build().dao

        val vehicleRepo = VehicleRepositoryImpl(dao)
        val viewModel = NotifViewModel(vehicleRepo)

        provideContent {
            WidgetScreen(viewModel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun WidgetScreen(viewModel: NotifViewModel) {
        LaunchedEffect(Unit) { viewModel.getAllVehicles() }

        Box(
            contentAlignment = Alignment.Center,
            modifier = GlanceModifier
                .background(ColorPalette.background)
                .fillMaxSize()
                .clickable { actionStartActivity<MainActivity>() },
        ) {
            Column {
                if (viewModel.expirations.intValue != 0) {
                    StatDisplay(
                        value = viewModel.expirations.intValue,
                        type = DisplayType.EXPIRATION,
                    )
                }

                if (viewModel.warnings.intValue != 0 && viewModel.expirations.intValue != 0) {
                    Spacer(GlanceModifier.height(16.dp))
                }

                if (viewModel.warnings.intValue != 0) {
                    StatDisplay(
                        value = viewModel.warnings.intValue,
                        type = DisplayType.WARNING,
                    )
                }

                if (viewModel.expirations.intValue == 0 && viewModel.warnings.intValue == 0) {
                    StatDisplay(
                        value = null,
                        type = DisplayType.ACTIVE,
                    )
                }
            }
        }
    }

    @Composable
    private fun StatDisplay(value: Int?, type: DisplayType) {
        Box(
            modifier = GlanceModifier.background(
                ColorProvider(
                    when (type) {
                        DisplayType.EXPIRATION -> ColorPalette.lightRed.copy(alpha = 0.1f)
                        DisplayType.WARNING -> ColorPalette.yellow.copy(alpha = 0.1f)
                        DisplayType.ACTIVE -> Color(0xFF53E215).copy(alpha = 0.1f)
                    }
                )
            )
                .cornerRadius(5.dp)
                .padding(
                    horizontal = 10.dp,
                    vertical = 5.dp,
                )
        ) {
            Text(
                text = when (value) {
                    null -> ":)"
                    else -> "$value"
                },
                style = TextStyle(
                    color = when (type) {
                        DisplayType.EXPIRATION -> ColorProvider(ColorPalette.lightRed)
                        DisplayType.WARNING -> ColorProvider(ColorPalette.yellow)
                        DisplayType.ACTIVE -> ColorProvider(Color(0xFF53E215))
                    },
                    fontFamily = FontFamily("inter"),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                )
            )
        }
    }
}