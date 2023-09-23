package io.fixmyride

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.fixmyride.database.DatabaseManager
import io.fixmyride.database.PrefsManager
import io.fixmyride.ui.screens.AddVehicleScreen
import io.fixmyride.ui.screens.EditVehicleScreen
import io.fixmyride.ui.screens.HomeScreen
import io.fixmyride.ui.screens.NotificationsScreen
import io.fixmyride.ui.screens.PreviewVehicleScreen
import io.fixmyride.ui.screens.SettingsScreen
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.FixMyRideTheme


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DatabaseManager.initialize(this)
        PrefsManager.initialize(this)
        
        val prefs = PrefsManager.getInstance()
        if (prefs.getInt("notifications_days", -1) == -1) {
            prefs.edit().putInt("notifications_days", 7).apply()
        }

        setContent {
            FixMyRideTheme {
                Surface(
                    color = ColorPalette.background,
                    modifier = Modifier.fillMaxSize(),
                ) { App() }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
private fun App() {
    val navCtrl = rememberNavController()

    NavHost(
        navController = navCtrl,
        startDestination = "/home",
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
    ) {
        composable("/home") { HomeScreen(navCtrl) }
        composable("/notifications") { NotificationsScreen(navCtrl) }
        composable("/settings") { SettingsScreen(navCtrl) }
        composable("/add-vehicle") { AddVehicleScreen(navCtrl) }
        composable(
            route = "/edit-vehicle/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
        ) {
            val vehicleId = it.arguments?.getString("id")
            EditVehicleScreen(navCtrl, vehicleId!!.toInt())
        }

        composable(
            route = "/selected-vehicle/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
        ) {
            val vehicleId = it.arguments?.getString("id")
            PreviewVehicleScreen(navCtrl, vehicleId!!.toInt())
        }
    }
}
